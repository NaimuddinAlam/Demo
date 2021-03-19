package com.fserp.kki.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fserp.kki.model.pojo.AllData
import com.fserp.kki.repository.CenterRepository

class CenteriewModel() {
     var mutableList: MutableLiveData<List<AllData>>?=null
    var centerRepository: CenterRepository
    init {
        centerRepository= CenterRepository()
    }
    fun centers(comcode:String?=null):LiveData<List<AllData>>
    {
        if(mutableList == null)
        {
            mutableList=centerRepository.center(comcode)
        }
        return mutableList!!
    }


}