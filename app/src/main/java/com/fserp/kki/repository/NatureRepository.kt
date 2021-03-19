package com.fserp.kki.repository

import androidx.lifecycle.MutableLiveData
import com.fserp.kki.model.pojo.AllData
import com.fserp.kki.model.pojo.Responses
import com.fserp.kki.utlis.Praments
import com.fserp.kki.utlis.network.APIClients
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NatureRepository {
    fun nature(
        comcode: String,
        naturecode: String,
        rnds: Int,
        userid: String,
        cent_code: String,
        type: String
    ): MutableLiveData<List<AllData>> {
        val mutableLiveData: MutableLiveData<List<AllData>>? = MutableLiveData()
        val praments = Praments()
        praments.PCOMP_CODE = comcode
        praments.PCENT_CODE = cent_code
        praments.PLOCONO = ""
        praments.PBRANCH_CODE=""
        praments.PCOMPLAINT_ID=rnds.toString()
        praments.PROW_ID=""
        praments.PNATURE_CODE=naturecode
        praments.PX1=""
        praments.PX2=""
        praments.PX3=""
        praments.PX4=""
        praments.PX5=""
        praments.PSTATUS=""
        praments.PIP=""
        praments.PUSER_ID=userid
        praments.PTYPE=type
        APIClients.newsInstance.addnature(praments)?.enqueue(object : Callback<Responses?> {

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