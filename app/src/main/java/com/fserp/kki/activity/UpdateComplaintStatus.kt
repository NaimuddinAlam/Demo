package com.fserp.kki.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fserp.kki.R
import com.fserp.kki.adapter.EquipmentAdapter
import com.fserp.kki.databinding.ActivityUpdateComplaintStatusBinding
import com.fserp.kki.model.pojo.AllData
import com.fserp.kki.model.pojo.Responses
import com.fserp.kki.utlis.Constants
import com.fserp.kki.utlis.Praments
import com.fserp.kki.utlis.SesssionManager
import com.fserp.kki.utlis.network.APIClients
import com.fserp.kki.viewmodel.*
import com.toptoche.searchablespinnerlibrary.SearchableSpinner
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class UpdateComplaintStatus : AppCompatActivity() {

    var binding: ActivityUpdateComplaintStatusBinding? = null
    var toolbar: Toolbar? = null
    var assignto: String? = null
    var itemcode: String? = null
    var itemcode1: String? = null
    var status: String? = null
    var USERNAME: String? = null
    val calender = Calendar.getInstance()
    val year = calender[Calendar.YEAR]
    val month = calender[Calendar.MONTH]
    val date = calender[Calendar.DAY_OF_MONTH]
    val hours = calender[Calendar.HOUR]
    val mintus = calender[Calendar.MINUTE]
    var bt_save: Button? = null
    var recyclerview: RecyclerView? = null
    var lis: List<AllData>? = null
    var show: AlertDialog? = null
    var key:String?=null

    var name: String? = null
    var code: String? = null
    var sppart: Spinner?=null
    var sp:SearchableSpinner?=null
    var sp1:SearchableSpinner?=null
    var COMPLAINT_ID:String?=null
    var array= arrayOf("Replace","Repaired")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showui()

    }



    fun showui() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_update_complaint_status)
        toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Update Complaint Status"

        binding?.btcom?.setOnClickListener {

            val alert = AlertDialog.Builder(this)
            val view: View = layoutInflater.inflate(R.layout.part, null)
            bt_save = view.findViewById(R.id.bt_save)
            sppart=view.findViewById(R.id.sppart)
            sp=view.findViewById(R.id.sp)
            sp1=view.findViewById(R.id.sp1)
            var etamount:TextView=view.findViewById(R.id.etamount)
            alert.setCancelable(true)
            alert.setView(view)
            sppart!!.adapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,array)

            sppart!!.onItemSelectedListener=object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    val item=p0!!.selectedItem.toString()
                  try {
                      if(item.equals("Replace"))
                      {
                          key="RET"
                      }else if(item.equals("Repaired"))
                      {
                          key="REP"
                      }
                  }catch (e:Exception){}

                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }
            item()

            item1()
            bt_save!!.setOnClickListener {
                try {
                    items(key,etamount.text.toString())
                    Log.d("texts", "msg" + CreateBy1.lists.toString())
                    show?.dismiss()

                } catch (e: Exception) {
                }


            }

            show = alert.show()


        }

        assignComplain(intent.getStringExtra("id").toString())
        assignto()
        status()
        equipment()
    }

    fun assignto() {
        AssigtoViewModel().assingto(SesssionManager.ReadSharePrefrence(this, Constants.COMP_CODE))
            .observe(
                this,
                object :
                    Observer<List<AllData>> {

                    override fun onChanged(t: List<AllData>?) {

                        if (t != null && t.size >= 0) {
                            val arrays = ArrayList<String>()
                            arrays.add("Select Ass..")
                            for (ts in t) {
                                arrays.add(ts.USERNAME.toString())


                            }
                            val adapte = ArrayAdapter(
                                this@UpdateComplaintStatus,
                                android.R.layout.simple_list_item_1,
                                arrays
                            )
                            binding?.spAssgin?.adapter = adapte
                            try {
                                if (USERNAME != null) {
                                    val id: Int = adapte.getPosition(USERNAME)
                                    binding?.spAssgin?.setSelection(id)

                                }
                            } catch (e: Exception) {
                            }
                            binding?.spAssgin?.onItemSelectedListener = object :
                                AdapterView.OnItemSelectedListener {
                                override fun onItemSelected(
                                    p0: AdapterView<*>?,
                                    p1: View?,
                                    p2: Int,
                                    p3: Long
                                ) {
                                    try {
                                        assignto = t[p2 - 1].USERID
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

    fun item() {
        ItemViewModel().item(SesssionManager.ReadSharePrefrence(this, Constants.COMP_CODE))
            .observe(
                this,
                object :
                    Observer<List<AllData>> {

                    override fun onChanged(t: List<AllData>?) {

                        if (t != null && t.size >= 0) {
                            val arrays = ArrayList<String>()
                            arrays.add("Select Item..")
                            for (ts in t) {
                                arrays.add(ts.ITEM_NAME.toString())


                            }
                            val adapte = ArrayAdapter(
                                this@UpdateComplaintStatus,
                                android.R.layout.simple_list_item_1,
                                arrays
                            )
                            sp?.adapter = adapte

                            sp?.onItemSelectedListener = object :
                                AdapterView.OnItemSelectedListener {
                                override fun onItemSelected(
                                    p0: AdapterView<*>?,
                                    p1: View?,
                                    p2: Int,
                                    p3: Long
                                ) {
                                    try {
                                        itemcode = t[p2 - 1].ITEM_CODE
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
    fun item1() {
        Item1ViewModel().item1(SesssionManager.ReadSharePrefrence(this, Constants.COMP_CODE))
            .observe(
                this,
                object :
                    Observer<List<AllData>> {

                    override fun onChanged(t: List<AllData>?) {

                        if (t != null && t.size >= 0) {
                            val arrays = ArrayList<String>()
                            arrays.add("Select Item..")
                            for (ts in t) {
                                arrays.add(ts.LEDGER_NAME.toString())


                            }
                            val adapte = ArrayAdapter(
                                this@UpdateComplaintStatus,
                                android.R.layout.simple_list_item_1,
                                arrays
                            )
                            sp1?.adapter = adapte

                            sp1?.onItemSelectedListener = object :
                                AdapterView.OnItemSelectedListener {
                                override fun onItemSelected(
                                    p0: AdapterView<*>?,
                                    p1: View?,
                                    p2: Int,
                                    p3: Long
                                ) {
                                    try {
                                        itemcode1 = t[p2 - 1].LEDGER_CODE_SUBCODE
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
    fun status() {
        StatusViewModel().status(SesssionManager.ReadSharePrefrence(this, Constants.COMP_CODE))
            .observe(
                this,
                object :
                    Observer<List<AllData>> {

                    override fun onChanged(t: List<AllData>?) {

                        if (t != null && t.size >= 0) {
                            val arrays = ArrayList<String>()
                            arrays.add("Select Ass..")
                            for (ts in t) {
                                arrays.add(ts.STATUS_DESC.toString())


                            }
                            val adapte = ArrayAdapter(
                                this@UpdateComplaintStatus,
                                android.R.layout.simple_list_item_1,
                                arrays
                            )
                            binding?.spstatus?.adapter = adapte
                            try {
                                if (status != null) {
                                    val id: Int = adapte.getPosition(status)
                                    binding?.spstatus?.setSelection(id)

                                }
                            } catch (e: Exception) {
                            }
                            binding?.spstatus?.onItemSelectedListener = object :
                                AdapterView.OnItemSelectedListener {
                                override fun onItemSelected(
                                    p0: AdapterView<*>?,
                                    p1: View?,
                                    p2: Int,
                                    p3: Long
                                ) {
                                    try {
                                        assignto = t[p2 - 1].USERID
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

    fun equipment() {
        EquipmentViewModel().equipment(SesssionManager.ReadSharePrefrence(this, Constants.COMP_CODE))
            .observe(
                this,
                object :
                    Observer<List<AllData>> {

                    override fun onChanged(t: List<AllData>?) {

                        if (t != null && t.size >= 0) {
                            val arrays = ArrayList<String>()
                            arrays.add("Select Euipment")
                            for (ts in t) {
                                arrays.add(ts.EQUIP_NAME.toString())


                            }
                            val adapte = ArrayAdapter(
                                this@UpdateComplaintStatus,
                                android.R.layout.simple_list_item_1,
                                arrays
                            )
                            binding?.spNatureoffailure?.adapter = adapte
                            try {
                                /*if (status != null) {
                                    val id: Int = adapte.getPosition(status)
                                    binding?.spNatureoffailure?.setSelection(id)

                                }*/
                            } catch (e: Exception) {
                            }
                            binding?.spNatureoffailure?.onItemSelectedListener = object :
                                AdapterView.OnItemSelectedListener {
                                override fun onItemSelected(
                                    p0: AdapterView<*>?,
                                    p1: View?,
                                    p2: Int,
                                    p3: Long
                                ) {
                                    try {
                                        name = t[p2 - 1].EQUIP_NAME
                                        code = t[p2 - 1].EQUIP_CODE
                                        show(name,code)
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

    fun assignComplain(id: String) {

        AssignComplaintViewModel().assignComplain(
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            SesssionManager.ReadSharePrefrence(this, Constants.USER_ID),
            SesssionManager.ReadSharePrefrence(this, Constants.CENT_CODE),
            SesssionManager.ReadSharePrefrence(this, Constants.COMP_CODE),
            "",
            "",
            "", "GE",
            "", "", "", "", "", id,
        "").observe(
            this,
            object :
                Observer<List<AllData>> {

                override fun onChanged(t: List<AllData>?) {
                    Log.d("alll", "nsg" + t?.size)

                    try {
                        for (ts in t!!) {
                            binding?.etcomplainid?.setText(ts.COMPLAINT_ID.toString())
                            binding?.etdates?.setText(ts.COMPLAINT_DATE.toString())
                            binding?.ettimes?.setText(ts.COMPLAINT_TIME.toString())
                            USERNAME = ts.USERNAME.toString()
                            status=ts.STATUS_DESC.toString()
                            COMPLAINT_ID=ts.COMPLAINT_ID.toString()
                            binding?.etdatestatus?.setText(date.toString() + "/" + (month + 1) + "/" + year)
                            //strat_date = (month + 1).toString() + " /" + date.toString() + "/ " + year
                            val time = String.format("%02d", calender.get(Calendar.HOUR_OF_DAY)).toString() + ":" +
                                    String.format("%02d", calender.get(Calendar.MINUTE))
                            binding?.ettimestatus?.setText(time)
                        }
                    } catch (e: Exception) {
                    }
                    {

                    }


                }

            })
    }

    @SuppressLint("WrongConstant")
    fun show(name: String?, code: String?)
    {
        val alert = AlertDialog.Builder(this)
        val view: View = layoutInflater.inflate(R.layout.complainnature, null)
        bt_save = view.findViewById(R.id.bt_save)
        recyclerview = view.findViewById(R.id.recyclerview)
        recyclerview?.layoutManager = LinearLayoutManager(
            this@UpdateComplaintStatus,
            LinearLayout.VERTICAL,
            false
        )


        alert.setCancelable(true)
        alert.setView(view)
        val praments = Praments()
        praments.PCOMPCODE = SesssionManager.ReadSharePrefrence(this, Constants.COMP_CODE)
        praments.PFAILURE_NICK = name
        praments.PEQUIPMENTTYPE_CODE= code
        APIClients.newsInstance.getFailure(praments)?.enqueue(object :
            Callback<Responses?> {

            override fun onResponse(call: Call<Responses?>, response: Response<Responses?>) {
                if (response.isSuccessful) {

                    lis = response.body()?.response?.data

                    recyclerview?.adapter = EquipmentAdapter(this@UpdateComplaintStatus, lis, this@UpdateComplaintStatus)
                }
            }

            override fun onFailure(call: Call<Responses?>, t: Throwable) {

            }

        })
        bt_save!!.setOnClickListener {   show?.dismiss() }
        show = alert.show()

    }

    fun items(key: String?, amount: String)
    {
        CompPartViewModel().compart(
          SesssionManager.ReadSharePrefrence(this, Constants.USER_ID),
          SesssionManager.ReadSharePrefrence(this, Constants.CENT_CODE),
          SesssionManager.ReadSharePrefrence(this, Constants.COMP_CODE),
           itemcode!!,itemcode1!!,
          key!!, amount,COMPLAINT_ID!!,"I").observe(this,object : Observer<List<AllData>> {


          override fun onChanged(t: List<AllData>?) {
              for(ts in t!!)
              {
                  if(ts.RES.equals("Y"))
                  {
                      Log.d("add","ss"+ts.RES)
                  }
              }
          }

      })




    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.update,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        val intent = Intent(this@UpdateComplaintStatus, Assigned::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)

    }

}