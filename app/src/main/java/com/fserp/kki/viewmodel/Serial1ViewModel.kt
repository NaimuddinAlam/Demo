package com.fserp.kki.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fserp.kki.model.pojo.AllData
import com.fserp.kki.repository.Serial1Repository

class Serial1ViewModel {
    var  mutableList:MutableLiveData<List<AllData>>?=null
    var serial1Repository= Serial1Repository()
    init {
        serial1Repository= Serial1Repository()
    }
    fun serial1(comcode: String,locono:String):LiveData<List<AllData>>
    {
        if(mutableList==null)
        {
            mutableList=serial1Repository.serial1(comcode,locono)
        }
        return  mutableList!!
    }
}