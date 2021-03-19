package com.fserp.kki.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fserp.kki.model.pojo.AllData
import com.fserp.kki.repository.ItemRepository
import com.fserp.kki.repository.LocoRepository

class ItemViewModel {
    var mutableList: MutableLiveData<List<AllData>>?=null
    var itemRepository= ItemRepository()
    init {
        itemRepository= ItemRepository()
    }
    fun item(comcode: String): LiveData<List<AllData>>
    {
        if(mutableList==null)
        {
            mutableList=itemRepository.item(comcode)
        }
        return mutableList!!
    }
}