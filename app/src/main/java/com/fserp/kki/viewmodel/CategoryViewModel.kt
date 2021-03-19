package com.fserp.kki.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fserp.kki.model.pojo.AllData
import com.fserp.kki.repository.CategoryRepository
import com.fserp.kki.repository.PriorityRepository

class CategoryViewModel {
    var mutableList: MutableLiveData<List<AllData>>?=null
    var categoryRepository= CategoryRepository()
    init {
        categoryRepository= CategoryRepository()
    }
    fun category(comcode: String): LiveData<List<AllData>>
    {
        mutableList=categoryRepository.category(comcode)
        return  mutableList!!
    }

}