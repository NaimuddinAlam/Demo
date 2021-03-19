package com.fserp.kki.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fserp.kki.model.pojo.AllData
import com.fserp.kki.repository.ProductRepository

class ProductViewModel {
    var mutableList:MutableLiveData<List<AllData>>?=null
    var productRepository=ProductRepository()
    init {
        productRepository= ProductRepository()
    }
    fun product(comcode: String):LiveData<List<AllData>>
    {
        mutableList=productRepository.product(comcode)
        return  mutableList!!
    }
}