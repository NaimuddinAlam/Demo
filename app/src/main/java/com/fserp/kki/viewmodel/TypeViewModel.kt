package com.fserp.kki.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fserp.kki.model.pojo.AllData
import com.fserp.kki.repository.TypeRepository

class TypeViewModel {
    var typeRepository = TypeRepository()
    var mutableList: MutableLiveData<List<AllData>>? = null

    init {
        typeRepository = TypeRepository()
    }

    fun types(comcode: String): LiveData<List<AllData>> {
        if (mutableList == null) {
            mutableList = typeRepository.type(comcode)
        }
        return mutableList!!
    }
}