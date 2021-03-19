package com.fserp.kki.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fserp.kki.model.pojo.AllData
import com.fserp.kki.repository.AssigntoRepository
import com.fserp.kki.repository.CategoryRepository

class AssigtoViewModel {

    var mutableList: MutableLiveData<List<AllData>>?=null
    var assigntoRepository= AssigntoRepository()
    init {
        assigntoRepository= AssigntoRepository()
    }
    fun assingto(comcode: String): LiveData<List<AllData>>
    {
        mutableList=assigntoRepository.assingto(comcode)
        return  mutableList!!
    }
}