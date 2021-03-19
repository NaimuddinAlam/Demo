package com.fserp.kki.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fserp.kki.model.pojo.AllData
import com.fserp.kki.repository.PriorityRepository
import com.fserp.kki.repository.ProductRepository

class PriorityViewModel {
    var mutableList: MutableLiveData<List<AllData>>?=null
    var priorityRepository= PriorityRepository()
    init {
        priorityRepository= PriorityRepository()
    }
    fun Priority(comcode: String): LiveData<List<AllData>>
    {
        mutableList=priorityRepository.Priority(comcode)
        return  mutableList!!
    }
}