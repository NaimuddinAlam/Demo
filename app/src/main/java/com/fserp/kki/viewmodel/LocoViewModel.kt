package com.fserp.kki.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fserp.kki.model.pojo.AllData
import com.fserp.kki.repository.LocoRepository

class LocoViewModel {
    var mutableList:MutableLiveData<List<AllData>>?=null
    var locoRepository= LocoRepository()
    init {
        locoRepository= LocoRepository()
    }
    fun loco(comcode: String,shedcode:String):LiveData<List<AllData>>
    {
        if(mutableList==null)
        {
            mutableList=locoRepository.Loco(comcode,shedcode)
        }
        return mutableList!!
    }
}