package com.fserp.kki.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fserp.kki.model.pojo.AllData
import com.fserp.kki.repository.AssigntoRepository
import com.fserp.kki.repository.EquipmentRepository

class EquipmentViewModel {

    var mutableList: MutableLiveData<List<AllData>>?=null
    var equipmentRepository= EquipmentRepository()
    init {
        equipmentRepository= EquipmentRepository()
    }
    fun equipment(comcode: String): LiveData<List<AllData>>
    {
        mutableList=equipmentRepository.equipment(comcode)
        return  mutableList!!
    }
}