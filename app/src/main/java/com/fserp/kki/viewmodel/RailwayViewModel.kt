package com.fserp.kki.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fserp.kki.model.pojo.AllData
import com.fserp.kki.repository.RailwayRepository

class RailwayViewModel() {
    var mutableList:MutableLiveData<List<AllData>>?=null
    var railwayRepository=RailwayRepository()
    init {
        railwayRepository= RailwayRepository()
    }
    fun railway(comcode:String?=null,ledgername:String,mledgercode:String,typeflag:String):LiveData<List<AllData>>
    {
       if(mutableList==null)
       {
           mutableList=railwayRepository.railway(comcode,ledgername,mledgercode,typeflag)
       }
        return mutableList!!
    }
}