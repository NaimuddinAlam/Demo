package com.fserp.kki.repository

import androidx.lifecycle.MutableLiveData
import com.fserp.kki.model.pojo.AllData
import com.fserp.kki.model.pojo.Responses
import com.fserp.kki.utlis.Praments
import com.fserp.kki.utlis.network.APIClients
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CompPostRepository {

    fun compPost(
        tempno:String,
        PPOST_DATE:String,
        PPOST_TIME:String,
        assignto:String,
        PPOST_USER_ID:String,
        PCOMPLAINT_ID:String,
        PUSER_ID:String,
        PCENT_CODE:String,
        PCOMP_CODE:String,



       /* PPOST_ID:String,
        PPOST_TYPE:String,
        PBRANCH_CODE:String,
        PPOST_COMMENTS_SEARCH:String,
        PACTION_TAKEN_REMARKS:String,
        PSOLUTION_REMARKS:String,
        PATTACH_DOC_NAME:String,
        PATTACH_DOC_SIZE:String,
        PATTACH_DOC_TYPE:String,
        PEMAIL_FROM:String,
        PEMAIL_TO:String,
        PEMAIL_CC:String,
        PSENT_STATUS_MAIL:String,
        PSTATUS:String,
        PD_STATUS:String,

        PTYPE:String*/
    ): MutableLiveData<List<AllData>> {
        val mutableLiveData: MutableLiveData<List<AllData>>? = MutableLiveData()
        val praments = Praments()
        praments.PCOMP_CODE=PCOMP_CODE
        praments.PCENT_CODE=PCENT_CODE
        praments.PBRANCH_CODE=""
        praments.PCOMPLAINT_ID=PCOMPLAINT_ID
        praments.PPOST_ID=""
        praments.PPOST_DATE=PPOST_DATE
        praments.PPOST_TIME=PPOST_TIME
        praments.PPOST_USER_ID=PPOST_USER_ID
        praments.PPOST_TYPE="N"
        praments.PPOST_COMMENTS=""
        praments.PPOST_COMMENTS_SEARCH=""
        praments.PACTION_TAKEN_REMARKS=""
        praments.PSOLUTION_REMARKS=""
        praments.PATTACH_DOC_NAME=""
        praments.PATTACH_DOC_SIZE=""
        praments.PATTACH_DOC_TYPE=""
        praments.PEMAIL_FROM=""
        praments.PEMAIL_TO=""
        praments.PEMAIL_CC=""
        praments.X1=""
        praments.X2=""
        praments.X3=tempno
        praments.X4=""
        praments.X5=""
        praments.X6=""
        praments.X7=""
        praments.X8=""
        praments.X9=""
        praments.X10=""
        praments.X11=""
        praments.X12=""
        praments.X13=""
        praments. X14=""
        praments.PSENT_STATUS_MAIL=""
        praments.PSTATUS=""
        praments.PD_STATUS=""
        praments.PIP=""
        praments.PUSER_ID=PUSER_ID
        praments.PTYPE="I"
        APIClients.newsInstance.getCompPost(praments)?.enqueue(object :
            Callback<Responses?> {

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