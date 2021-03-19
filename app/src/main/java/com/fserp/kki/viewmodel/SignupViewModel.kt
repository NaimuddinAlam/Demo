package com.fserp.kki.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fserp.kki.model.login.LoginData
import com.fserp.kki.repository.SignupRepository

class SignupViewModel()
{
    var signupRepository: SignupRepository
    var mutableList: MutableLiveData<LoginData>?=null
    init {
        signupRepository=SignupRepository()
    }
    fun signups(comcode: String?, centercodes: String?, username: String, password: String):LiveData<LoginData>
    {
        if(mutableList == null) {
            mutableList = signupRepository.signup(comcode,centercodes,username,password)
        }
        return mutableList!!
    }
}
