package com.fserp.kki.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fserp.kki.model.image.ImagePojo
import com.fserp.kki.repository.ImageRepository
import okhttp3.MultipartBody
import okhttp3.RequestBody

class ImageViewModel {

    var mutableList: MutableLiveData<ImagePojo>? = null
    var imageRepository = ImageRepository()

    init {
        imageRepository = ImageRepository()
    }

    fun saveImage(tyep: RequestBody,tyep1: RequestBody,tyep2: RequestBody,tyep3: RequestBody,tyep4: RequestBody,tyep5: RequestBody,tyep6: RequestBody,tyep7: RequestBody,tyep8: RequestBody,tyep9: RequestBody,tyep10: RequestBody,tyep11: RequestBody,tyep12: RequestBody,tyep13: RequestBody,
        COMP_CODE: RequestBody,
        Cent_code: RequestBody,
        userid: RequestBody,
        tempn: RequestBody,
        createby: RequestBody,
        filename1: RequestBody?,
        profile_photo: MultipartBody.Part
    ): LiveData<ImagePojo> {
        if (mutableList == null) {
            mutableList = imageRepository.ImageSave(tyep,tyep1,tyep2,tyep3,tyep4,tyep5,tyep6,tyep7,tyep8,tyep9,tyep10,tyep11,tyep12,tyep13,
                COMP_CODE,
                Cent_code,
                userid,
                tempn,
                createby,
                filename1,
            profile_photo)
        }
        return mutableList!!
    }
}