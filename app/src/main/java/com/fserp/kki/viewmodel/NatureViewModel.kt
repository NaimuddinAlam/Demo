package com.fserp.kki.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fserp.kki.model.pojo.AllData
import com.fserp.kki.repository.NatureRepository

class NatureViewModel {

    var mutableList: MutableLiveData<List<AllData>>?=null
    var natureRepository= NatureRepository()
    init {
        natureRepository= NatureRepository()
    }
    fun nature(
        comcode: String,
        naturecode: String,
        rnds: Int,
        userid: String,
        cent_code: String,
        type: String
    ): LiveData<List<AllData>>
    {
        if(mutableList==null)
        {
            mutableList=natureRepository.nature(comcode,naturecode,rnds,userid,cent_code,type)
        }
        return mutableList!!
    }
}