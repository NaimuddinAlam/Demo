package com.fserp.kki.repository

import androidx.lifecycle.MutableLiveData
import com.fserp.kki.model.pojo.AllData
import com.fserp.kki.model.pojo.Responses
import com.fserp.kki.utlis.Praments
import com.fserp.kki.utlis.network.APIClients
import com.fserp.kki.utlis.network.APIRequests
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CenterRepository {

    fun center(comcode:String?=null): MutableLiveData<List<AllData>> {
        val mutableLiveData: MutableLiveData<List<AllData>> = MutableLiveData()

        val praments = Praments()
        praments.pcenter = ""
        praments.extra = ""
        praments.pcompcode ="KKI"
        APIClients.newsInstance.getCenter(praments)?.enqueue(object :
                Callback<Responses?> {

                override fun onResponse(call: Call<Responses?>, response: Response<Responses?>) {
                    if (response.isSuccessful) {
                        try {
                            mutableLiveData.value = response.body()?.response?.data
                        } catch (e: Exception) {
                        }

                    }

                }


                override fun onFailure(call: Call<Responses?>, t: Throwable) {

                }

            })
        return mutableLiveData
    }
}