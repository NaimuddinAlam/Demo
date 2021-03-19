package com.fserp.kki.repository

import androidx.lifecycle.MutableLiveData
import com.fserp.kki.model.pojo.AllData
import com.fserp.kki.model.pojo.Responses
import com.fserp.kki.utlis.Praments
import com.fserp.kki.utlis.network.APIClients
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RailwayRepository {

    fun railway(comcode:String?=null,ledgername:String,mledgercode:String,typeflag:String): MutableLiveData<List<AllData>> {
        val mutableLiveData: MutableLiveData<List<AllData>> = MutableLiveData()
        val praments = Praments()
        praments.pcompcode = comcode
        praments.pcentcode = ""
        praments.ledgername = ledgername
        praments.mledgercode =mledgercode
        praments.typeflag = typeflag
        praments.exptra1 = ""

        APIClients.newsInstance.getRailwayShed(praments)?.enqueue(object : Callback<Responses?> {

            override fun onResponse(call: Call<Responses?>, response: Response<Responses?>) {

                mutableLiveData.value = response.body()?.response?.data
            }


            override fun onFailure(call: Call<Responses?>, t: Throwable) {

            }

        })

        return mutableLiveData
    }
}