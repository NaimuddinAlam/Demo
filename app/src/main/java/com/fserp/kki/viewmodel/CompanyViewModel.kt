package com.fserp.kki.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fserp.kki.model.pojo.AllData
import com.fserp.kki.repository.CompanyRepository

class CompanyViewModel(var mutableList: MutableLiveData<List<AllData>>? = null) {



    var companyRepository: CompanyRepository? = null

    init {
        companyRepository = CompanyRepository()
    }

    fun companydata(): LiveData<List<AllData>> {
        if (mutableList == null) {
            mutableList = companyRepository!!.company()
        }
        return mutableList!!
    }
}