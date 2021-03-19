package com.fserp.kki.repository

import androidx.lifecycle.MutableLiveData
import com.fserp.kki.model.pojo.AllData
import com.fserp.kki.model.pojo.Responses
import com.fserp.kki.utlis.Praments
import com.fserp.kki.utlis.network.APIClients
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CompPartRepository {

    fun compart(usrid: String,
                CENT_CODE: String,
                COMP_CODE: String,
                itemcode: String,
                itemcode1: String,
                key: String,
                amount: String,
                complain: String,
    type:String): MutableLiveData<List<AllData>>
    {
        val mutableLiveData: MutableLiveData<List<AllData>> = MutableLiveData()
        val praments= Praments()
        praments.PCOMP_CODE=COMP_CODE
        praments.PCENT_CODE=CENT_CODE
        praments.PBRANCH_CODE=""
        praments.PCOMPLAINT_ID=complain
        praments.PROW_ID=""
        praments.PSTYPE=key
        praments.PITEM_CODE=itemcode
        praments.PLEDGER_CODE_SUBCODE=itemcode1
        praments.PCOST=amount
        praments.PUSER_ID=usrid
        praments.PTYPE=type
        APIClients.newsInstance.getBindCompPart(praments)?.enqueue(object : Callback<Responses?> {

            override fun onResponse(call: Call<Responses?>, response: Response<Responses?>) {
                if(response.isSuccessful) {
                    mutableLiveData.value = response.body()?.response?.data
                }
            }

            override fun onFailure(call: Call<Responses?>, t: Throwable) {

            }

        })
        return mutableLiveData
    }
}