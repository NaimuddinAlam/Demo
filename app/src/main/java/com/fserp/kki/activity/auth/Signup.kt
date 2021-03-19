package com.fserp.kki.activity.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.fserp.kki.R
import com.fserp.kki.activity.DashBoard
import com.fserp.kki.databinding.ActivitySignupBinding
import com.fserp.kki.model.login.LoginData
import com.fserp.kki.model.pojo.AllData
import com.fserp.kki.utlis.Constants
import com.fserp.kki.utlis.Internets
import com.fserp.kki.utlis.SesssionManager
import com.fserp.kki.utlis.SesssionManager.Companion.WriteSharePrefrence
import com.fserp.kki.viewmodel.CenteriewModel
import com.fserp.kki.viewmodel.CompanyViewModel
import com.fserp.kki.viewmodel.SignupViewModel
import java.util.*


class Signup : AppCompatActivity() {
    var activitySignupBinding: ActivitySignupBinding? = null
    var comcode: String? = null
    var centercodes: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showui()
    }

    fun showui() {
        activitySignupBinding = DataBindingUtil.setContentView(this, R.layout.activity_signup)

        company()

        activitySignupBinding!!.btlog.setOnClickListener {

            next()

        }


    }

    fun next() {

        if (comcode.isNullOrEmpty()) {
            Toast.makeText(this,     resources.getString(R.string.selectcompany), Toast.LENGTH_SHORT).show()
        } else if (centercodes.isNullOrEmpty()) {
            Toast.makeText(this,  resources.getString(R.string.selectcenter), Toast.LENGTH_SHORT).show()
        } else if (activitySignupBinding?.etusername?.text.isNullOrEmpty()) {
            Toast.makeText(this, resources.getString(R.string.entername), Toast.LENGTH_SHORT).show()
        } else if (activitySignupBinding?.etpassword?.text.isNullOrEmpty()) {
            Toast.makeText(this,  resources.getString(R.string.enterpass), Toast.LENGTH_SHORT).show()
        } else {
            if (Internets.hasNetworkAccess(this)) {
                sigup()
            } else {
                Toast.makeText(
                    this,
                    resources.getString(R.string.could_not_connect),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }


    }


    fun company() {
        if (Internets.hasNetworkAccess(this)) {
            CompanyViewModel().companydata().observe(this, object : Observer<List<AllData>> {

                override fun onChanged(t: List<AllData>?) {
                    if (t != null && t.size >= 0) {

                        var arrayLists = ArrayList<String>()
                        arrayLists.add("Select Company")
                        for (ts in t) {
                            arrayLists.add(ts.COMP_NAME!!)
                        }
                        val adapter = ArrayAdapter(
                            this@Signup,
                            android.R.layout.simple_list_item_1,
                            arrayLists
                        )
                        activitySignupBinding?.spCom?.adapter = adapter
                        activitySignupBinding?.spCom?.onItemSelectedListener = object :
                            AdapterView.OnItemSelectedListener {
                            override fun onItemSelected(
                                p0: AdapterView<*>?,
                                p1: View?,
                                p2: Int,
                                p3: Long
                            ) {
                                try {
                                    comcode = t[p2 - 1].COMP_CODE
                                    Toast.makeText(this@Signup, comcode, Toast.LENGTH_SHORT).show()
                                    centers(comcode!!)

                                } catch (e: Exception) {
                                }


                            }

                            override fun onNothingSelected(p0: AdapterView<*>?) {

                            }

                        }

                    }


                }

            })
        } else {
            Toast.makeText(
                this,
                resources.getString(R.string.could_not_connect),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun centers(comcode: String) {
        CenteriewModel().centers(comcode).observe(this, object : Observer<List<AllData>> {

            override fun onChanged(t: List<AllData>?) {
                if (t != null && t.size >= 0) {
                    var arrays = arrayListOf<String>()
                    arrays.add("Select Center")
                    for (ts in t) {
                        arrays.add(ts.CENT_NAME!!)
                    }
                    activitySignupBinding?.spCenter?.adapter =
                        ArrayAdapter(this@Signup, android.R.layout.simple_list_item_1, arrays)
                    activitySignupBinding?.spCenter?.onItemSelectedListener = object :
                        AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            p0: AdapterView<*>?,
                            p1: View?,
                            p2: Int,
                            p3: Long
                        ) {
                            try {
                                centercodes = t[p2 - 1].CENT_CODE!!

                            } catch (e: Exception) {
                            }

                        }

                        override fun onNothingSelected(p0: AdapterView<*>?) {

                        }

                    }
                }
            }

        })
    }

    fun sigup() {
        SignupViewModel().signups(
            comcode, centercodes,
            activitySignupBinding?.etusername?.text.toString(),
            activitySignupBinding?.etpassword?.text.toString()
        ).observe(this, object : Observer<LoginData> {

            override fun onChanged(ts: LoginData?) {
                try {
                    if (ts!!.rES.equals("Y")) {
                        SesssionManager(this@Signup).setUser(ts)
                        WriteSharePrefrence(this@Signup, Constants.USER_ID, ts.uSERID!!)
                        WriteSharePrefrence(this@Signup, Constants.USER_NAME, ts.uSERNAME!!)
                        WriteSharePrefrence(this@Signup, Constants.COMP_CODE, ts.cOMPCODE!!)
                        WriteSharePrefrence(this@Signup, Constants.CENT_CODE, ts.CENT_CODE!!)
                        WriteSharePrefrence(this@Signup, Constants.USER_Email, ts.eMAIL!!)
                        WriteSharePrefrence(this@Signup, Constants.TYPE, ts.tYPE!!)
                        WriteSharePrefrence(this@Signup, Constants.Mobile, ts.mOBILE!!)
                        val intent = Intent(this@Signup, DashBoard::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(intent)
                    } else {
                        if (ts.rES.equals("N")) {
                            Toast.makeText(this@Signup,  resources.getString(R.string.invaliduser), Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                } catch (e: Exception) {
                }

            }

        })
    }
}







