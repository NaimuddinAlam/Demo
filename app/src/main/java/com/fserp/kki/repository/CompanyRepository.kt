package com.fserp.kki.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.fserp.kki.model.pojo.AllData
import com.fserp.kki.model.pojo.Responses
import com.fserp.kki.utlis.Praments
import com.fserp.kki.utlis.network.APIClients
import com.fserp.kki.utlis.network.APIRequests
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CompanyRepository {

    fun company(): MutableLiveData<List<AllData>> {

        val mutableLiveData: MutableLiveData<List<AllData>> = MutableLiveData()

        val parameter = Praments()
        parameter.PCOMPNAME = ""
        APIClients.newsInstance.getcompany(parameter)
            ?.enqueue(object :
                Callback<Responses?> {

                override fun onResponse(call: Call<Responses?>, response: Response<Responses?>) {
                    if (response.isSuccessful) {
                        mutableLiveData.value = response.body()?.response?.data
                    }


                }


                override fun onFailure(call: Call<Responses?>, t: Throwable) {

                }

            })
        return mutableLiveData
    }

}