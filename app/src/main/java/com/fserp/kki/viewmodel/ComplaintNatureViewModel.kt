package com.fserp.kki.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fserp.kki.model.pojo.AllData
import com.fserp.kki.repository.AssigntoRepository
import com.fserp.kki.repository.ComplaintNatureRepository

class ComplaintNatureViewModel {
    var mutableList: MutableLiveData<List<AllData>>?=null
    var complaintNatureRepository= ComplaintNatureRepository()
    init {
        complaintNatureRepository= ComplaintNatureRepository()
    }
    fun complaintnature(comcode: String): LiveData<List<AllData>>
    {
        mutableList=complaintNatureRepository.complaintnature(comcode)
        return  mutableList!!
    }
}