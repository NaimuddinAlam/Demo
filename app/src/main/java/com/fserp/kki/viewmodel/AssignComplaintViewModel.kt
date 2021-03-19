package com.fserp.kki.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fserp.kki.model.pojo.AllData
import com.fserp.kki.repository.AssignComplaintRepository

class AssignComplaintViewModel {
    var mutableList: MutableLiveData<List<AllData>>? = null
    var assignComplaintRepository = AssignComplaintRepository()

    init {
        assignComplaintRepository = AssignComplaintRepository()
    }

    fun assignComplain(
        date: String,
        type_code: String,
        strat_dateex: String,
        mledgercodeSS: String,
        shed_code: String,
        loco_code: String,
        serial_code: String,
        etwarrpo: String,
        etwarrshed: String,
        etmgfwarr1: String,
        etmgfwarr2: String,
        etcomsum: String,
        ettimes: String,
        procode: String,
        etrepby: String,
        catecode: String,
        prioritycode: String,
        assignto: String,
        etcomdetails: String,
        rnds: String,
        USER_ID: String,
        CENT_CODE: String,
        COMP_CODE: String,
        serial_code1: String,
        LEDGER_CODE_SUBCODE: String,
        tempno:String,
        types:String,
        CUST: String,
        status: String,
        DEPTT: String,
        rgb: String,
        statusd:String,
        complaiid:String,
        search:String
    ): LiveData<List<AllData>> {
        if (mutableList == null) {
            mutableList = assignComplaintRepository.assignComplain(
                date,
                type_code,
                strat_dateex,
                mledgercodeSS,
                shed_code,
                loco_code,
                serial_code,
                etwarrpo,
                etwarrshed,
                etmgfwarr1,
                etmgfwarr2,
                etcomsum,
                ettimes,
                procode,
                etrepby,
                catecode,
                prioritycode,
                assignto,
                etcomdetails,
                rnds,
                USER_ID,
                CENT_CODE,
                COMP_CODE,
                LEDGER_CODE_SUBCODE,
                serial_code1,
                tempno,
                types,
                CUST,
                status,
                DEPTT,
                rgb,
                statusd,
                complaiid,
                search

            )
        }
        return mutableList!!
    }
}