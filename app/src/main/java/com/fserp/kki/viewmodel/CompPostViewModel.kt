package com.fserp.kki.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fserp.kki.model.pojo.AllData
import com.fserp.kki.repository.CompPostRepository

class CompPostViewModel {
    var mutableList: MutableLiveData<List<AllData>>?=null
    var compPostRepository= CompPostRepository()
    init {
        compPostRepository= CompPostRepository()
    }
    fun CompPost(
        tempno: String,
        date: String,
        ettimes: String,
        assignto:String,

        etcomdetails: String,
        complaintId: String,
        USER_ID: String,
        CENT_CODE: String,
        COMP_CODE: String,


    ): LiveData<List<AllData>>
    {
        if(mutableList==null)
        {
            mutableList=compPostRepository.compPost(
                tempno,
                date,
                ettimes,
                assignto,
                etcomdetails,
                complaintId,
                USER_ID,
                CENT_CODE,
                COMP_CODE
            )
        }
        return mutableList!!
    }

}