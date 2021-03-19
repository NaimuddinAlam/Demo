package com.fserp.kki.repository

import androidx.lifecycle.MutableLiveData
import com.fserp.kki.model.pojo.AllData
import com.fserp.kki.model.pojo.Responses
import com.fserp.kki.utlis.Praments
import com.fserp.kki.utlis.network.APIClients
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Item1Repository {
    fun item1(comcode: String): MutableLiveData<List<AllData>>
    {
        val mutableLiveData: MutableLiveData<List<AllData>> = MutableLiveData()
        val praments= Praments()
        praments.pcompcode=comcode
        praments.pcentcode=""
        praments.ledgername=""
        praments.mledgercode=""
        praments.typeflag=""

        APIClients.newsInstance.getBindVendor(praments)?.enqueue(object : Callback<Responses?> {

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