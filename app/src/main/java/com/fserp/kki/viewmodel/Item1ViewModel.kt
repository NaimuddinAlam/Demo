package com.fserp.kki.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fserp.kki.model.pojo.AllData
import com.fserp.kki.repository.Item1Repository
import com.fserp.kki.repository.ItemRepository

class Item1ViewModel {

    var mutableList: MutableLiveData<List<AllData>>?=null
    var itemRepository= Item1Repository()
    init {
        itemRepository= Item1Repository()
    }
    fun item1(comcode: String): LiveData<List<AllData>>
    {
        if(mutableList==null)
        {
            mutableList=itemRepository.item1(comcode)
        }
        return mutableList!!
    }
}