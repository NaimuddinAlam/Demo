package com.fserp.kki.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fserp.kki.model.pojo.AllData
import com.fserp.kki.repository.CompPartRepository
import com.fserp.kki.repository.Item1Repository

class CompPartViewModel {

    var mutableList: MutableLiveData<List<AllData>>?=null
    var compPartRepository= CompPartRepository()
    init {
        compPartRepository= CompPartRepository()
    }
    fun compart(
        usrid: String,
        CENT_CODE: String,
        COMP_CODE: String,
        itemcode: String,
        itemcode1: String,
        key: String,
        amount: String,
        complain: String,
        type: String
    ): LiveData<List<AllData>>
    {
        if(mutableList==null)
        {
            mutableList=compPartRepository.compart(usrid,
                CENT_CODE,
                COMP_CODE,
                itemcode,
                itemcode1,
                key,amount,complain,type)
        }
        return mutableList!!
    }
}