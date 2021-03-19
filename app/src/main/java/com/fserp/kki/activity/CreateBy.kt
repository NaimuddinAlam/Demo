package com.fserp.kki.activity

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.fserp.kki.R
import com.fserp.kki.databinding.ActivityCreateByBinding
import com.fserp.kki.model.pojo.AllData
import com.fserp.kki.utlis.Constants
import com.fserp.kki.utlis.Internets
import com.fserp.kki.utlis.SesssionManager
import com.fserp.kki.viewmodel.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class CreateBy : AppCompatActivity() {
    var binding: ActivityCreateByBinding? = null
    var toolbar: Toolbar? = null
    val calender = Calendar.getInstance()
    val year = calender[Calendar.YEAR]
    val month = calender[Calendar.MONTH]
    val date = calender[Calendar.DAY_OF_MONTH]
    val hours = calender[Calendar.HOUR]
    val mintus = calender[Calendar.MINUTE]
    var railwaycode: String? = null
    var shedcode: String? = null
    var lococode: String? = null
    var comcode: String? = null
    var ledgername: String = ""
    var mledgercode: String = ""
    var ledgernameSS: String = ""
    var mledgercodeSS: String = ""
    var typeflag: String? = null
    var locos: String = ""
    var strat_date: String? = null
    var type_code: String? = null
    var strat_dateex: String? = null
    var shed_code: String? = null
    var loco_code: String? = null
    var serial_code: String? = null
    var serialcode2: String? = null
    var LEDGER_CODE_SUBCODE: String? = null
    var etwarrshed: String?=null
    var etmgfwarr1: String?=null
    var etmgfwarr2: String?=null
    var etexdates: String?=null
    var etwarrpo: String?=null
    companion object {
        val rnds = (0..1000).random()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showui()
    }

    fun showui() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_by)
        toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Created By"


        insertall()
        if (Internets.hasNetworkAccess(this)) {
            type()
            railway("")
            //shed("")

        } else {

            Toast.makeText(
                this,
                resources.getString(R.string.could_not_connect),
                Toast.LENGTH_SHORT
            ).show()

        }

    }

    @SuppressLint("SetTextI18n")
    fun insertall() {

        val second = calender[Calendar.SECOND]
        val datess = (month + 1).toString() + "/" + date + "/" + year
        binding?.etdates?.setText(date.toString() + "/" + (month + 1) + "/" + year)
        strat_date = (month + 1).toString() + " /" + date.toString() + "/ " + year
        val time = String.format("%02d", calender.get(Calendar.HOUR_OF_DAY)).toString() + ":" +
                String.format("%02d", calender.get(Calendar.MINUTE))
        binding?.ettimes?.setText(time)
        //  binding?.ettimes?.setText(String.format("%02d:%02d", hours, mintus))
        binding?.etdates?.setOnClickListener {
            val datePickerDialog =
                DatePickerDialog(this@CreateBy, { view, year, month, dayOfMonth ->
                    var formdate = dayOfMonth.toString() + " " + (month + 1) + " " + year
                    strat_date = (month + 1).toString() + " /" + dayOfMonth.toString() + "/ " + year
                    val start_date1 = dayOfMonth.toString() + "/" + (month + 1) + "/" + year
                    binding!!.etdates.setText(start_date1)

                }, year, month, date)
            datePickerDialog.show()
            datePickerDialog.datePicker.maxDate = calender.timeInMillis
        }
        binding?.etexdates?.setOnClickListener {

            val datePickerDialog =
                DatePickerDialog(this@CreateBy, { view, year, month, dayOfMonth ->
                    var formdate = dayOfMonth.toString() + " " + (month + 1) + " " + year
                    // strat_dateex = (month + 1).toString() + " /" + dayOfMonth.toString() + "/ " + year
                    val start_date1 = dayOfMonth.toString() + "/" + (month + 1) + "/" + year
                    binding?.etexdates?.setText("")
                    binding!!.etexdates.setText(start_date1)
                }, year, month, date)
            datePickerDialog.show()
            datePickerDialog.datePicker.maxDate = calender.timeInMillis
        }


        binding?.ettimes?.setOnClickListener(View.OnClickListener {
            val timePickerDialog = TimePickerDialog(this@CreateBy, { view, hourOfDay, minute ->
                var hourOfDay = hourOfDay
                if (hourOfDay == 0) {
                    hourOfDay += 12
                    //format = "AM";
                } else if (hours == 12) {
                    //format = "PM";
                } else if (hours > 12) {
                    hourOfDay -= 12
                    // format = "PM"
                } else {
                    //  format = "AM";
                }
                binding?.ettimes?.setText(String.format("%02d:%02d", hourOfDay, minute))

            }, hours, mintus, false)
            timePickerDialog.show()
        })

       binding?.btnext?.setOnClickListener {
        /*    val intent = Intent(this@CreateBy, CreateBy1::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)*/
            validation()
        }
    }

    fun validation() {
        if (type_code.isNullOrEmpty()) {
            val toast = Toast.makeText(this, "Please Select Complaint Type", Toast.LENGTH_LONG)
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()

        } else if (mledgercodeSS.isNullOrEmpty()) {
            val toast = Toast.makeText(this, "Please Select Railway Type", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()
        } else if (shed_code.isNullOrEmpty()) {
            val toast = Toast.makeText(this, "Please Select Shed Type", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()
        } else if (loco_code.isNullOrEmpty()) {
            val toast = Toast.makeText(this, "Please Select Loco Type", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()
        } else if (binding?.etcomsum?.text.toString().isNullOrEmpty()) {
            val toast = Toast.makeText(this, "Please Enter Complaint Summary", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()
        } else {
            try {




                val inputFormat: DateFormat = SimpleDateFormat("dd/MM/yyyy")
                val outputFormat: DateFormat = SimpleDateFormat("MM/dd/yyyy")

                val inputDateStr = binding?.etwarrpo?.text.toString()
                val date: Date = inputFormat.parse(inputDateStr)
                 etwarrpo = outputFormat.format(date)

                val inputdate = binding?.etwarrshed?.text.toString()
                val date1: Date = inputFormat.parse(inputdate)
                 etwarrshed = outputFormat.format(date1)

                val inputdates = binding?.etmgfwarr1?.text.toString()
                val date2: Date = outputFormat.parse(inputdates)
                 etmgfwarr1 = outputFormat.format(date2)
                val inputss = binding?.etmgfwarr2?.text.toString()
                val date3: Date = inputFormat.parse(inputss)
                 etmgfwarr2 = outputFormat.format(date3)

                val inputdatess = binding!!.etexdates.text.toString()
                val date4: Date = inputFormat.parse(inputdatess)
                 etexdates = outputFormat.format(date1)


                Log.d("text", "msg" + etwarrpo)

            }catch (e:Exception){}
            val intent = Intent(this@CreateBy, CreateBy1::class.java)
            intent.putExtra("date", strat_date)
            intent.putExtra("type_code", type_code!!)
            intent.putExtra("strat_dateex", etexdates)
            intent.putExtra("mledgercodeSS", mledgercodeSS)
            intent.putExtra("shed_code", shed_code)
            intent.putExtra("loco_code", loco_code!!)
            intent.putExtra("serial_code", serial_code)
            intent.putExtra("etwarrpo", etwarrpo)
            intent.putExtra("etwarrshed", etwarrshed)
            intent.putExtra("etmgfwarr1", etmgfwarr1)
            intent.putExtra("serialcode2", serialcode2)
            intent.putExtra("LEDGER_CODE_SUBCODE", LEDGER_CODE_SUBCODE!!)
            intent.putExtra("etmgfwarr2", etmgfwarr2)
            intent.putExtra("etcomsum", binding?.etcomsum?.text.toString())
            intent.putExtra("ettimes", binding?.ettimes?.text.toString().trim())

            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }


    }

    fun type() {
        TypeViewModel().types(SesssionManager.ReadSharePrefrence(this, Constants.COMP_CODE))
            .observe(this, object : Observer<List<AllData>> {

                override fun onChanged(t: List<AllData>?) {
                    if (t != null && t.size >= 0) {
                        val arrays = ArrayList<String>()
                        arrays.add("Select Type")
                        for (ts in t) {
                            arrays.add(ts.CMP_TYPE_NAME.toString())
                        }
                        binding?.sptype?.adapter =
                            ArrayAdapter(this@CreateBy, android.R.layout.simple_list_item_1, arrays)

                        binding?.sptype?.onItemSelectedListener = object :
                            AdapterView.OnItemSelectedListener {
                            override fun onItemSelected(
                                p0: AdapterView<*>?,
                                p1: View?,
                                p2: Int,
                                p3: Long
                            ) {
                                try {
                                    if (t.get(0).equals("Select Type")) {
                                        binding?.etexdates?.setText("")
                                    }

                                    binding?.etexdates?.setText("")
                                    type_code = t[p2 - 1].CMP_TYPE_CODE
                                    val date = t[p2 - 1].CLOSSINGDATE.toString()
                                    binding?.etexdates?.setText(date)
                                } catch (e: Exception) {
                                }

                            }

                            override fun onNothingSelected(p0: AdapterView<*>?) {
                                TODO("Not yet implemented")
                            }

                        }


                    }

                }

            })
    }

    fun railway(code: String) {
        RailwayViewModel().railway(
            SesssionManager.ReadSharePrefrence(this, Constants.COMP_CODE),
            "",
            code,
            "M"
        )
            .observe(this, object : Observer<List<AllData>> {
                override fun onChanged(t: List<AllData>?) {
                    try {
                        val arrays = ArrayList<String>()
                        arrays.add("Select Rail..")
                        if (t != null && t.size >= 0) {
                            for (ts in t) {
                                arrays.add(ts.LEDGER_NICK.toString())
                            }

                            binding?.sprailway?.adapter =
                                ArrayAdapter(
                                    this@CreateBy,
                                    android.R.layout.simple_list_item_1,
                                    arrays
                                )
                            binding?.sprailway?.onItemSelectedListener = object :
                                AdapterView.OnItemSelectedListener {
                                override fun onItemSelected(
                                    p0: AdapterView<*>?,
                                    p1: View?,
                                    p2: Int,
                                    p3: Long
                                ) {
                                    try {
                                        if (arrays[p2].equals("Select Rail..")) {
                                            shed("")
                                            loco("")
                                            serialno1("")
                                            serialno2("")
                                        } else {
                                            ledgernameSS = t[p2 - 1].LEDGER_NICK.toString()
                                            mledgercodeSS = t[p2 - 1].LEDGER_CODE.toString()
                                            shed(mledgercodeSS)
                                        }

                                    } catch (e: java.lang.Exception) {
                                    }

                                }

                                override fun onNothingSelected(p0: AdapterView<*>?) {
                                    TODO("Not yet implemented")
                                }

                            }
                        }


                    } catch (e: Exception) {
                    }

                }

            })
    }

    fun shed(mledgercodes: String) {
        RailwayViewModel().railway(
            SesssionManager.ReadSharePrefrence(this, Constants.COMP_CODE),
            "",
            mledgercodes, "S"
        )
            .observe(this, object : Observer<List<AllData>> {

                override fun onChanged(t: List<AllData>?) {
                    try {
                        val arrays = ArrayList<String>()
                        arrays.add("Select Shed")
                        if (t != null && t.size >= 0) {
                            for (ts in t) {
                                arrays.add((ts.LEDGER_NICK.toString()))
                            }
                            if (mledgercodes.equals("")) {
                                arrays.clear()
                            }

                            binding?.spshed?.adapter = ArrayAdapter(
                                this@CreateBy,
                                android.R.layout.simple_list_item_1, arrays
                            )
                            binding?.spshed?.onItemSelectedListener = object :
                                AdapterView.OnItemSelectedListener {
                                override fun onItemSelected(
                                    p0: AdapterView<*>?,
                                    p1: View?,
                                    p2: Int,
                                    p3: Long
                                ) {
                                    try {

                                        Log.d("shed1", "msg${arrays[p2]}")


                                        if (arrays[p2].equals("Select Shed")) {

                                            loco("")
                                            serialno1("")
                                            serialno2("")
                                        } else {

                                            ledgername = t[p2 - 1].LEDGER_NICK.toString()
                                            mledgercode = t[p2 - 1].LEDGER_CODE.toString()
                                            shed_code = t[p2 - 1].SUB_LEDGER_CODE.toString()
                                            LEDGER_CODE_SUBCODE =
                                                t[p2 - 1].LEDGER_CODE_SUBCODE.toString()
                                            loco(t[p2 - 1].LEDGER_CODE_SUBCODE.toString())
                                        }


                                        //  railway("")
                                    } catch (e: Exception) {
                                    }

                                }

                                override fun onNothingSelected(p0: AdapterView<*>?) {

                                }

                            }
                        }
                    } catch (e: java.lang.Exception) {
                    }

                }

            })
    }

    fun loco(shedcode: String) {
        LocoViewModel().loco(
            SesssionManager.ReadSharePrefrence(this, Constants.COMP_CODE), shedcode
        )
            .observe(this, object : Observer<List<AllData>> {
                override fun onChanged(t: List<AllData>?) {
                    try {
                        val arrays = ArrayList<String>()
                        Log.d("shed", "msg${shedcode}")

                        arrays.add("Select Loco..")
                        if (t != null && t.size >= 0) {
                            for (ts in t) {

                                arrays.add(ts.LOCO_NO.toString())
                            }
                            if (shedcode.equals("")) {
                                arrays.clear()

                            }

                            binding?.spLoco?.adapter =
                                ArrayAdapter(
                                    this@CreateBy,
                                    android.R.layout.simple_list_item_1,
                                    arrays
                                )
                            binding?.spLoco?.onItemSelectedListener = object :
                                AdapterView.OnItemSelectedListener {
                                override fun onItemSelected(
                                    p0: AdapterView<*>?,
                                    p1: View?,
                                    p2: Int,
                                    p3: Long
                                ) {
                                    try {
                                        if (arrays[p2].equals("Select Loco..")) {

                                            serialno1("")
                                            serialno2("")
                                        } else {
                                            loco_code = t[p2 - 1].ENTRY_NO
                                            serialno1(t[p2 - 1].ENTRY_NO.toString())
                                            serialno2(t[p2 - 1].ENTRY_NO)
                                        }

                                    } catch (e: java.lang.Exception) {
                                    }

                                }

                                override fun onNothingSelected(p0: AdapterView<*>?) {
                                    TODO("Not yet implemented")
                                }

                            }
                        }


                    } catch (e: Exception) {
                    }

                }

            })
    }

    fun serialno1(locono: String) {
        Serial1ViewModel().serial1(
            SesssionManager.ReadSharePrefrence(this, Constants.COMP_CODE),
            locono
        )
            .observe(this, object : Observer<List<AllData>> {

                override fun onChanged(t: List<AllData>?) {
                    try {
                        val arrays = ArrayList<String>()
                        arrays.add("Select SL1")

                        if (t != null && t.size >= 0) {
                            for (ts in t) {
                                arrays.add((ts.SERIALNO.toString()))
                            }
                            if (locono.equals("")) {
                                arrays.clear()

                            }
                            binding?.spserial1?.adapter = ArrayAdapter(
                                this@CreateBy,
                                android.R.layout.simple_list_item_1,
                                arrays
                            )
                            binding?.etwarrpo?.setText("")
                            binding?.etwarrshed?.setText("")
                            binding?.etmgfwarr1?.setText("")
                            binding?.spserial1?.onItemSelectedListener = object :
                                AdapterView.OnItemSelectedListener {
                                override fun onItemSelected(
                                    p0: AdapterView<*>?,
                                    p1: View?,
                                    p2: Int,
                                    p3: Long
                                ) {
                                    try {
                                        //lococode = t[p2 - 1].WARRANTY_OUT_DATE
                                        if (arrays[p2].equals("Select SL1")) {
                                            binding?.etwarrpo?.setText("")
                                            binding?.etwarrshed?.setText("")
                                            binding?.etmgfwarr1?.setText("")
                                        } else {
                                            serial_code = t[p2 - 1].SERIALNO
                                            binding?.etwarrpo?.setText(t[p2 - 1].WARRANTY_OUT_DATE)
                                            binding?.etwarrshed?.setText(t[p2 - 1].WARRANTY_COMM_DATE)
                                            binding?.etmgfwarr1?.setText(t[p2 - 1].WARRANTY_MFG_DATE)
                                        }


                                    } catch (e: Exception) {
                                    }

                                }

                                override fun onNothingSelected(p0: AdapterView<*>?) {

                                }

                            }
                        }
                    } catch (e: Exception) {
                    }
                }

            })
    }

    fun serialno2(locono: String?) {
        Serial1ViewModel().serial1(
            SesssionManager.ReadSharePrefrence(this, Constants.COMP_CODE),
            locono.toString()
        )
            .observe(this, object : Observer<List<AllData>> {

                override fun onChanged(t: List<AllData>?) {
                    try {
                        val arrays = ArrayList<String>()
                        arrays.add("Select SL2")
                        if (t != null && t.size >= 0) {
                            for (ts in t) {
                                arrays.add((ts.SERIALNO.toString()))
                            }
                            if (locono.equals("")) {
                                arrays.clear()

                            }
                            binding?.spserial2?.adapter = ArrayAdapter(
                                this@CreateBy,
                                android.R.layout.simple_list_item_1,
                                arrays
                            )

                            binding?.etmgfwarr2?.setText("")
                            binding?.etwarrshed?.setText("")
                            binding?.etwarrpo?.setText("")

                            binding?.spserial2?.onItemSelectedListener = object :
                                AdapterView.OnItemSelectedListener {
                                override fun onItemSelected(
                                    p0: AdapterView<*>?,
                                    p1: View?,
                                    p2: Int,
                                    p3: Long
                                ) {
                                    try {
                                        //lococode = t[p2 - 1].WARRANTY_OUT_DATE
                                        if (arrays[p2].equals("Select SL2")) {
                                            binding?.etmgfwarr2?.setText("")
                                            binding?.etwarrshed?.setText("")
                                            binding?.etwarrpo?.setText("")
                                        }
                                        serialcode2 = t[p2 - 1].SERIALNO
                                        binding?.etmgfwarr2?.setText(t[p2 - 1].WARRANTY_MFG_DATE)
                                        binding?.etwarrpo?.setText(t[p2 - 1].WARRANTY_OUT_DATE)
                                        binding?.etwarrshed?.setText(t[p2 - 1].WARRANTY_COMM_DATE)


                                    } catch (e: Exception) {
                                    }

                                }

                                override fun onNothingSelected(p0: AdapterView<*>?) {

                                }

                            }
                        }
                    } catch (e: Exception) {
                    }
                }

            })
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        val intent = Intent(this@CreateBy, DashBoard::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
}