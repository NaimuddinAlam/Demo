package com.fserp.kki.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.fserp.kki.model.login.LoginData
import com.fserp.kki.model.login.LoginResponse
import com.fserp.kki.utlis.Praments
import com.fserp.kki.utlis.network.APIClients
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupRepository {

    fun signup(comcode: String?, centercodes: String?, username: String, password: String): MutableLiveData<LoginData> {
        var mutableLiveData: MutableLiveData<LoginData> = MutableLiveData()
        val praments = Praments()
        praments.PCOMP_CODE = comcode
        praments.PCENT_CODE = centercodes
        praments.PUSER_NAME = username
        praments.PPASSWORD = password
        APIClients.newsInstance.getlogin(praments)?.enqueue(object : Callback<LoginResponse?> {

            override fun onResponse(call: Call<LoginResponse?>, response: Response<LoginResponse?>) {
                if (response.isSuccessful) {
                    Log.d("log","msg"+Gson().toJson(response.body()))
                    mutableLiveData.value =response.body()?.response?.data

                }
            }


            override fun onFailure(call: Call<LoginResponse?>, t: Throwable) {

            }

        })
        return mutableLiveData
    }
}