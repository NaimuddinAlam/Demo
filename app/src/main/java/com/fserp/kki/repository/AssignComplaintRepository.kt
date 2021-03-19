package com.fserp.kki.repository

import androidx.lifecycle.MutableLiveData
import com.fserp.kki.model.pojo.AllData
import com.fserp.kki.model.pojo.Responses
import com.fserp.kki.utlis.Praments
import com.fserp.kki.utlis.network.APIClients
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AssignComplaintRepository {
    fun assignComplain(
        date: String,
        type_code: String,
        strat_dateex: String,
        mledgercodeSS: String,
        shed_code: String,
        loco_code: String,
        serial_code: String,
        etwarrpo: String,
        etwarrshed: String,
        etmgfwarr1: String,
        etmgfwarr2: String,
        etcomsum: String,
        ettimes: String,
        procode: String,
        etrepby: String,
        catecode: String,
        prioritycode: String,
        assignto: String,
        etcomdetails: String,
        rnds: String,
        USER_ID: String,
        CENT_CODE: String,
        COMP_CODE: String,
        serial_code1: String,
        LEDGER_CODE_SUBCODE: String,
        tempno: String,
        types: String,
        CUST: String,
        status: String,
        DEPTT: String,
        rgb: String,
        statusd: String,
        complaiid: String,
       search:String
    ): MutableLiveData<List<AllData>> {
        val mutableLiveData: MutableLiveData<List<AllData>>? = MutableLiveData()
        val praments = Praments()
        praments.PCOMP_CODE = COMP_CODE
        praments.PCENT_CODE = CENT_CODE
        praments.PBRANCH_CODE = ""
        praments.PSTATUS = status
        praments.PIP = ""
        praments.PUSER_ID = USER_ID
        praments.PTYPE = types
        praments.PCOMPLAINT_TYPE = CUST
        praments.PCOMPLAINT_ID = complaiid
        praments.PCOMPLAINT_DATE = date
        praments.PCOMPLAINT_TIME = ettimes
        praments.PDEPTT_CODE = ""
        praments.PCMP_TYPE_CODE = type_code
        praments.PCUST_TYPE_CODE = DEPTT
        praments.PLEDGER_CODE = mledgercodeSS
        praments.PSUB_LEDGER_CODE = shed_code
        praments.PLEDGER_CODE_SUBCODE = LEDGER_CODE_SUBCODE
        praments.PLOCO_NO = loco_code
        praments.PPRODUCT_CODE = procode
        praments.PCOMPLAINT_SHORT_DESC = etcomsum
        praments.PREPORTED_BY = etrepby
        praments.PCATEGORY_CODE = catecode
        praments.PPRIORITY_CODE = prioritycode
        praments.PASSIGN_TO_CODE = assignto
        praments.PASSIGN_DATE = date
        praments.PASSIGN_TIME = ettimes
        praments.PCMP_CLOSSINGDATE = strat_dateex
        praments.PWARRANTY_OUT_DATE = etwarrpo
        praments.PWARRANTY_COMM_DATE = etwarrshed
        praments.PWARRANTY_MFG_DATE = etmgfwarr1
        praments.PCMP_NATURE = ""
        praments.PCMP_DETAILS = etcomdetails
        praments.X1 = serial_code
        praments.X2 = serial_code1
        praments.X3 = etmgfwarr2
        praments.X4 = ""
        praments.X5 = ""
        praments.PNOTIFICATION_FLAG = ""
        praments.PCOLOR_FLAG = rgb
        praments.PD_STATUS = statusd
        praments.PEXTRA1 = rnds
        praments.PEXTRA2 = ""
        praments.PEXTRA3 = tempno
        praments.PEXTRA4 = search
        praments.PEXTRA5 = ""
        praments.PDATEFORMAT = ""
        APIClients.newsInstance.addAssignComplaint(praments)
            ?.enqueue(object : Callback<Responses?> {

                override fun onResponse(call: Call<Responses?>, response: Response<Responses?>) {
                    if (response.isSuccessful) {
                        mutableLiveData?.value = response.body()?.response?.data
                    }
                }

                override fun onFailure(call: Call<Responses?>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        return mutableLiveData!!
    }


}