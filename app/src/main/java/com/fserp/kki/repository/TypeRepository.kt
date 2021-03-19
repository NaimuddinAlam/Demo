package com.fserp.kki.repository

import androidx.lifecycle.MutableLiveData
import com.fserp.kki.model.pojo.AllData
import com.fserp.kki.model.pojo.Responses
import com.fserp.kki.utlis.Praments
import com.fserp.kki.utlis.network.APIClients
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TypeRepository {

    fun type(comcode:String):MutableLiveData<List<AllData>>
    {
        val mutableLiveData:MutableLiveData<List<AllData>> = MutableLiveData()
        var praments= Praments()
        praments.pcompcode=comcode
        praments.PEXTRA=""
        praments.COMPLAINT_CODE=""
        praments.COMPLAINT_NAME=""
        praments.COMPLAINT_NICK=""


        APIClients.newsInstance.getType(praments)?.enqueue(object : Callback<Responses?> {

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