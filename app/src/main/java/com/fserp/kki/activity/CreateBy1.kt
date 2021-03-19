package com.fserp.kki.activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Build.VERSION_CODES.Q
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fserp.kki.R
import com.fserp.kki.adapter.ComAdapter
import com.fserp.kki.adapter.ComplintAdapter
import com.fserp.kki.adapter.ImageAdapter
import com.fserp.kki.databinding.ActivityCreateBy1Binding
import com.fserp.kki.model.ComplaintPojo
import com.fserp.kki.model.image.ImagePojo
import com.fserp.kki.model.pojo.AllData
import com.fserp.kki.model.pojo.Responses
import com.fserp.kki.utlis.*
import com.fserp.kki.utlis.network.APIClients
import com.fserp.kki.viewmodel.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class CreateBy1 : AppCompatActivity() {
    var binding: ActivityCreateBy1Binding? = null
    var toolbar: Toolbar? = null

    var recyclerview: RecyclerView? = null
    var recyclerview11: RecyclerView? = null
    var recyclerview111: RecyclerView? = null
    var show: AlertDialog? = null
    var bt_save: Button? = null
    var procode: String? = null
    var jsonArray: JSONArray? = null
    var catecode: String? = null
    var prioritycode: String? = null
    var assignto: String? = null
    var tempno: String? = null
    var file: File? = null
    var fils: String? = null
    val arrayList = ArrayList<ComplaintPojo>()

    companion object {
        var lists = ArrayList<String>()
        var data_list = ArrayList<String>()
        val PICK_FROM_GALLERY = 101
    }

    var lis: List<AllData>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        UI()
        showui()

    }

    @SuppressLint("WrongConstant")
    fun showui() {


        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_by1)
        toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Created By"
        arrayList.clear()
        jsonArray = JSONArray()
        addNature1("", "GE")


        recyclerview11 = findViewById(R.id.recyclerview11) as RecyclerView
        recyclerview11?.layoutManager = LinearLayoutManager(
            this@CreateBy1,
            LinearLayout.VERTICAL,
            false
        )
        recyclerview111 = findViewById(R.id.recyclerview111) as RecyclerView
        recyclerview111?.layoutManager = LinearLayoutManager(
            this@CreateBy1,
            LinearLayout.VERTICAL,
            false
        )

        binding?.btcom?.setOnClickListener {

            val alert = AlertDialog.Builder(this)
            val view: View = layoutInflater.inflate(R.layout.complainnature, null)
            bt_save = view.findViewById(R.id.bt_save)
            recyclerview = view.findViewById(R.id.recyclerview)
            recyclerview?.layoutManager = LinearLayoutManager(
                this@CreateBy1,
                LinearLayout.VERTICAL,
                false
            )


            alert.setCancelable(true)
            alert.setView(view)
            val praments = Praments()
            praments.PCOMPCODE = SesssionManager.ReadSharePrefrence(
                this,
                Constants.COMP_CODE
            )
            praments.NATURE = ""
            APIClients.newsInstance.getComplaintNature(praments)?.enqueue(object :
                Callback<Responses?> {

                override fun onResponse(call: Call<Responses?>, response: Response<Responses?>) {
                    if (response.isSuccessful) {

                        lis = response.body()?.response?.data

                        recyclerview?.adapter = ComAdapter(this@CreateBy1, lis, this@CreateBy1)
                    }
                }

                override fun onFailure(call: Call<Responses?>, t: Throwable) {

                }

            })


            bt_save!!.setOnClickListener {
                try {
                    // ComplaintNature(lists)
                    addNature1("", "GE")
                    Log.d("texts", "msg" + lists.toString())
                    show?.dismiss()

                } catch (e: Exception) {
                }


            }

            show = alert.show()


        }

        binding?.imagefile?.setOnClickListener {
            permission()
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.type = "*/*"
            intent.setAction(Intent.ACTION_GET_CONTENT)
            startActivityForResult(Intent.createChooser(intent, "Select File"), PICK_FROM_GALLERY)


        }

        binding?.btsave?.setOnClickListener {
            validation()
        }


        // multipleSelectionSpinner=findViewById(R.id.mSpinner) as MultipleSelectionSpinner
        if (Internets.hasNetworkAccess(this)) {


            product()
            priority()
            category()
            assignto()
            //ComplaintNature()

        } else {

            Toast.makeText(
                this,
                resources.getString(R.string.could_not_connect),
                Toast.LENGTH_SHORT
            ).show()

        }
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == PICK_FROM_GALLERY) {


            if (data != null) {
                //  data_list!!.add(File(FileNewPath.getPath(this@CreateBy1, data.data)))

                file = File(FileNewPath.getPath(this@CreateBy1, data.data))
                fils = file?.name?.substring(file!!.name.lastIndexOf("/") + 1)
                data_list.add(fils.toString())

                recyclerview111?.adapter = ImageAdapter(data_list, this@CreateBy1, this@CreateBy1)
                Log.d("size", "sm" + file)
                saveImage(fils, "I")

            }
        }
    }


    fun validation() {
        if (procode.isNullOrEmpty()) {
            val toast = Toast.makeText(this, "Please Select Product", Toast.LENGTH_LONG)
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()

        } else if (binding?.etrepby?.text.toString().isNullOrEmpty()) {
            val toast = Toast.makeText(this, "Please Enter Reported By ", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()
        } else if (catecode.isNullOrEmpty()) {
            val toast = Toast.makeText(this, "Please Select Category Type", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()
        } else if (prioritycode.isNullOrEmpty()) {
            val toast = Toast.makeText(this, "Please Select Priority Type", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()
        } else if (lis!!.isEmpty()) {
            val toast = Toast.makeText(this, "Please Select Complaint Nature", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()
        } else {

            val date = intent.getStringExtra("date")
            val type_code = intent.getStringExtra("type_code")
            val strat_dateex = intent.getStringExtra("strat_dateex")
            val mledgercodeSS = intent.getStringExtra("mledgercodeSS")
            val shed_code = intent.getStringExtra("shed_code")
            val loco_code = intent.getStringExtra("loco_code")
            val serial_code = intent.getStringExtra("serial_code")
            val etwarrpo = intent.getStringExtra("etwarrpo")
            val etwarrshed = intent.getStringExtra("etwarrshed")
            val etmgfwarr1 = intent.getStringExtra("etmgfwarr1")
            val serialcode2 = intent.getStringExtra("serialcode2")
            val etmgfwarr2 = intent.getStringExtra("etmgfwarr2")
            val etcomsum = intent.getStringExtra("etcomsum")
            val ettimes = intent.getStringExtra("ettimes")
            val LEDGER_CODE_SUBCODE = intent.getStringExtra("LEDGER_CODE_SUBCODE")

            assignComplain(
                date,
                type_code,
                strat_dateex,
                mledgercodeSS,
                shed_code,
                loco_code,
                serial_code,
                etwarrpo,
                etwarrshed,
                etmgfwarr1,
                etmgfwarr2,
                etcomsum,
                ettimes,
                LEDGER_CODE_SUBCODE.toString(),
                serialcode2,

            )
            Log.d(
                "all",
                "msh" + date + " " + type_code + "" + strat_dateex + "" + mledgercodeSS + "" + shed_code + "" +
                        loco_code + "" + serial_code + "" + etwarrpo + "" + etwarrshed + "" + etmgfwarr1 + "" + serialcode2 + "" + etmgfwarr2 + "" + etcomsum + "" +
                        ettimes
            )

        }


    }


    fun product() {
        ProductViewModel().product(SesssionManager.ReadSharePrefrence(this, Constants.COMP_CODE))
            .observe(
                this,
                object :
                    Observer<List<AllData>> {

                    override fun onChanged(t: List<AllData>?) {

                        if (t != null && t.size >= 0) {
                            val arrays = ArrayList<String>()
                            arrays.add("Select Product")
                            for (ts in t) {
                                arrays.add(ts.PRODUCT_DESC.toString())


                            }

                            binding?.spPro?.adapter =
                                ArrayAdapter(
                                    this@CreateBy1,
                                    android.R.layout.simple_list_item_1,
                                    arrays
                                )
                            binding?.spPro?.onItemSelectedListener = object :
                                AdapterView.OnItemSelectedListener {
                                override fun onItemSelected(
                                    p0: AdapterView<*>?,
                                    p1: View?,
                                    p2: Int,
                                    p3: Long
                                ) {
                                    try {
                                        procode = t[p2 - 1].PRODUCT_CODE
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

    fun priority() {
        PriorityViewModel().Priority(SesssionManager.ReadSharePrefrence(this, Constants.COMP_CODE))
            .observe(
                this,
                object :
                    Observer<List<AllData>> {

                    override fun onChanged(t: List<AllData>?) {

                        if (t != null && t.size >= 0) {
                            val arrays = ArrayList<String>()
                            arrays.add("Select pri..")
                            for (ts in t) {
                                arrays.add(ts.PRIORITY_DESC.toString())


                            }
                            binding?.spPriority?.adapter =
                                ArrayAdapter(
                                    this@CreateBy1,
                                    android.R.layout.simple_list_item_1,
                                    arrays
                                )
                            binding?.spPriority?.onItemSelectedListener = object :
                                AdapterView.OnItemSelectedListener {
                                override fun onItemSelected(
                                    p0: AdapterView<*>?,
                                    p1: View?,
                                    p2: Int,
                                    p3: Long
                                ) {
                                    try {
                                        prioritycode = t[p2 - 1].PRIORITY_CODE
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

    fun category() {
        CategoryViewModel().category(SesssionManager.ReadSharePrefrence(this, Constants.COMP_CODE))
            .observe(
                this,
                object :
                    Observer<List<AllData>> {

                    override fun onChanged(t: List<AllData>?) {

                        if (t != null && t.size >= 0) {
                            val arrays = ArrayList<String>()
                            arrays.add("Select Category")
                            for (ts in t) {
                                arrays.add(ts.CATEGORY_DESC.toString())


                            }
                            binding?.spCategory?.adapter =
                                ArrayAdapter(
                                    this@CreateBy1,
                                    android.R.layout.simple_list_item_1,
                                    arrays
                                )
                            binding?.spCategory?.onItemSelectedListener = object :
                                AdapterView.OnItemSelectedListener {
                                override fun onItemSelected(
                                    p0: AdapterView<*>?,
                                    p1: View?,
                                    p2: Int,
                                    p3: Long
                                ) {
                                    try {
                                        catecode = t[p2 - 1].CATEGORY_CODE
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
                            binding?.spAssign?.adapter =
                                ArrayAdapter(
                                    this@CreateBy1,
                                    android.R.layout.simple_list_item_1,
                                    arrays
                                )
                            binding?.spAssign?.onItemSelectedListener = object :
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

    fun addNature(naturecode: String, type: String) {

        NatureViewModel().nature(
            SesssionManager.ReadSharePrefrence(this, Constants.COMP_CODE),
            naturecode,
            CreateBy.rnds,
            SesssionManager.ReadSharePrefrence(this, Constants.USER_ID),
            SesssionManager.ReadSharePrefrence(this, Constants.CENT_CODE),
            type
        )
            .observe(
                this,
                object :
                    Observer<List<AllData>> {

                    override fun onChanged(t: List<AllData>?) {
                        //   lis = t
                        for (ts in t!!) {
                            if (ts.RES.equals("Y")) {
                                //Toast.makeText(this@CreateBy1,"add",Toast.LENGTH_SHORT).show()
                            }
                        }


                    }

                })
    }

    fun addNature1(naturecode: String, type: String) {

        NatureViewModel().nature(
            SesssionManager.ReadSharePrefrence(this, Constants.COMP_CODE),
            naturecode,
            CreateBy.rnds,
            SesssionManager.ReadSharePrefrence(this, Constants.USER_ID),
            SesssionManager.ReadSharePrefrence(this, Constants.CENT_CODE),
            type
        )
            .observe(
                this,
                object :
                    Observer<List<AllData>> {

                    override fun onChanged(t: List<AllData>?) {
                        lis = t
                        recyclerview11?.adapter = ComplintAdapter(this@CreateBy1, t, this@CreateBy1)


                    }

                })
    }


    fun assignComplain(
        date: String?,
        type_code: String?,
        strat_dateex: String?,
        mledgercodeSS: String?,
        shed_code: String?,
        loco_code: String?,
        serial_code: String?,
        etwarrpo: String?,
        etwarrshed: String?,
        etmgfwarr1: String?,
        etmgfwarr2: String?,
        etcomsum: String?,
        ettimes: String?,
        LEDGER_CODE_SUBCODE: String,
        serial_code1: String?


    ) {

        AssignComplaintViewModel().assignComplain(
            date.toString(),
            type_code.toString(),
            strat_dateex.toString(),
            mledgercodeSS.toString(),
            shed_code.toString(),
            loco_code.toString(),
            serial_code.toString(),
            etwarrpo.toString(),
            etwarrshed.toString(),
            etmgfwarr1.toString(),
            etmgfwarr2.toString(),
            etcomsum.toString(),
            ettimes.toString(),
            procode.toString(),
            binding?.etrepby?.text.toString(),
            catecode.toString(),
            prioritycode.toString(),
            assignto.toString(),
            binding?.etcomdetails?.text.toString(),
            CreateBy.rnds.toString(),
            SesssionManager.ReadSharePrefrence(this, Constants.USER_ID),
            SesssionManager.ReadSharePrefrence(this, Constants.CENT_CODE),
            SesssionManager.ReadSharePrefrence(this, Constants.COMP_CODE),
            LEDGER_CODE_SUBCODE.toString(),
            serial_code1.toString(),
            tempno.toString(),
        "I",
            "CUST","N","DEPTT","rgb(255, 255, 255)","N","",""
        ).observe(
            this,
            object :
                Observer<List<AllData>> {

                override fun onChanged(t: List<AllData>?) {

                    for (ts in t!!) {
                        if (ts.RES.equals("Y")) {
                            CompPost(
                                date.toString(),
                                ettimes.toString(),
                                ts.COMPLAINT_ID
                            )


                        }
                    }


                }

            })
    }

    fun CompPost(
        date: String?,
        time: String?,
        complaintId: String?
    ) {
        CompPostViewModel().CompPost(
            "",
            date.toString(),
            time.toString(),
            assignto.toString(),
            binding?.etcomdetails?.text.toString(),
            complaintId.toString(),
            SesssionManager.ReadSharePrefrence(this, Constants.USER_ID),
            SesssionManager.ReadSharePrefrence(this, Constants.CENT_CODE),
            SesssionManager.ReadSharePrefrence(this, Constants.COMP_CODE)

        ).observe(
            this,
            object :
                Observer<List<AllData>> {

                override fun onChanged(t: List<AllData>?) {

                    for (ts in t!!) {
                        if (ts.RES.equals("Y")) {


                            val intent = Intent(this@CreateBy1, DashBoard::class.java)
                            intent.flags =
                                Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                            startActivity(intent)
                        }
                    }


                }

            })
    }

    fun UI() {
        val c = Calendar.getInstance()
        val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        tempno = df.format(c.time)
    }

    fun saveImage(fils: String?, type: String) {
        var profile_photo: MultipartBody.Part? = null
        val proof_photo: MultipartBody.Part? = null

        var filename1: RequestBody? = null
        val COMP_CODE: RequestBody = RequestBody.create(
            MultipartBody.FORM, SesssionManager.ReadSharePrefrence(
                this,
                Constants.COMP_CODE
            )
        )
        val Cent_code: RequestBody = RequestBody.create(
            MultipartBody.FORM, SesssionManager.ReadSharePrefrence(
                this,
                Constants.CENT_CODE
            )
        )
        val userid: RequestBody = RequestBody.create(
            MultipartBody.FORM, SesssionManager.ReadSharePrefrence(
                this,
                Constants.USER_ID
            )
        )
        val tempn: RequestBody = RequestBody.create(MultipartBody.FORM, tempno.toString())
        val createby = RequestBody.create(MultipartBody.FORM, type)
        val tyep = RequestBody.create(MultipartBody.FORM, "")
        val tyep1 = RequestBody.create(MultipartBody.FORM, "")
        val tyep2 = RequestBody.create(MultipartBody.FORM, "")
        val tyep3 = RequestBody.create(MultipartBody.FORM, "")
        val tyep4 = RequestBody.create(MultipartBody.FORM, "")
        val tyep5 = RequestBody.create(MultipartBody.FORM, "")
        val tyep6 = RequestBody.create(MultipartBody.FORM, "")
        val tyep7 = RequestBody.create(MultipartBody.FORM, "")
        val tyep8 = RequestBody.create(MultipartBody.FORM, "")
        val tyep9 = RequestBody.create(MultipartBody.FORM, "")
        val tyep10 = RequestBody.create(MultipartBody.FORM, "")
        val tyep11 = RequestBody.create(MultipartBody.FORM, "")
        val tyep12 = RequestBody.create(MultipartBody.FORM, "")
        val tyep13 = RequestBody.create(MultipartBody.FORM, "")
        val tyep14 = RequestBody.create(MultipartBody.FORM, "")


        if (file != null) {
            var requestBody: RequestBody = RequestBody.create("*/*".toMediaTypeOrNull(), file!!)
            profile_photo =
                MultipartBody.Part.createFormData(
                    MultipartBody.FORM.toString(),
                    this.fils!!,
                    requestBody
                )

            filename1 = RequestBody.create(MultipartBody.FORM, this.fils!!)
        } else {

            profile_photo = MultipartBody.Part.createFormData(MultipartBody.FORM.toString(), "")

        }

        ImageViewModel().saveImage(
            tyep,
            tyep1,
            tyep2,
            tyep3,
            tyep4,
            tyep5,
            tyep6,
            tyep7,
            tyep8,
            tyep9,
            tyep10,
            tyep11,
            tyep12,
            tyep13,
            COMP_CODE,
            Cent_code,
            userid,
            tempn,
            createby,
            filename1,
            profile_photo
        ).observe(this, object :
            Observer<ImagePojo> {

            override fun onChanged(t: ImagePojo?) {
                if (t!!.errorMessage.equals("Y")) {
                    Toast.makeText(this@CreateBy1, "add", Toast.LENGTH_SHORT).show()
                }

            }

        })


    }


    fun permission(): Boolean {
        if (Build.VERSION.SDK_INT >= Q) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
            ) {
                return true
            }
            else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ),
                    6
                )
            }
                return false
            }
        return true



}


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        val intent = Intent(this@CreateBy1, DashBoard::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
}
