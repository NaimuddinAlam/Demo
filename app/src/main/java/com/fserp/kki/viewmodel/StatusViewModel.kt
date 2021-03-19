package com.fserp.kki.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fserp.kki.model.pojo.AllData
import com.fserp.kki.repository.AssigntoRepository
import com.fserp.kki.repository.StatusRepository

class StatusViewModel {
    var mutableList: MutableLiveData<List<AllData>>?=null
    var statusRepository= StatusRepository()
    init {
        statusRepository= StatusRepository()
    }
    fun status(comcode: String): LiveData<List<AllData>>
    {
        mutableList=statusRepository.status(comcode)
        return  mutableList!!
    }
}