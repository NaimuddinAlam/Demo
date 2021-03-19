package com.fserp.kki.utlis.network

import com.fserp.kki.model.image.ResponseImage
import com.fserp.kki.model.login.LoginResponse
import com.fserp.kki.model.pojo.Responses
import com.fserp.kki.utlis.Praments
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface APIRequests {


    // 1 Company
    @Headers("Content-Type: application/json;charset=UTF-8")
    @POST("Getbindcomp")
    fun getcompany(@Body praments: Praments?): Call<Responses?>?

    // 2 Center
    @Headers("Content-Type: application/json;charset=UTF-8")
    @POST("GetbindCenter")
    fun getCenter(@Body praments: Praments?): Call<Responses?>?

    // 3 Login
    @Headers("Content-Type: application/json;charset=UTF-8")
    @POST("GetLoginDetails")
    fun getlogin(@Body praments: Praments?): Call<LoginResponse?>?

    // 4 Type
    @Headers("Content-Type: application/json;charset=UTF-8")
    @POST("GetComplaintType")
    fun getType(@Body praments: Praments?): Call<Responses?>?

    // 5 railway & shed

    @Headers("Content-Type: application/json;charset=UTF-8")
    @POST("GetBindLedger")
    fun getRailwayShed(@Body praments: Praments?): Call<Responses?>?

    // 6 serial no1 & serialno2

    @Headers("Content-Type: application/json;charset=UTF-8")
    @POST("GetbindSerial")
    fun getSerialno1(@Body praments: Praments?): Call<Responses?>?

    // 7 loco

    @Headers("Content-Type: application/json;charset=UTF-8")
    @POST("Getbindloco")
    fun getLoco(@Body praments: Praments?): Call<Responses?>?

    // 8 product

    @Headers("Content-Type: application/json;charset=UTF-8")
    @POST("GetbindProd")
    fun getProduct(@Body praments: Praments?): Call<Responses?>?

    // 9 priority
    @Headers("Content-Type: application/json;charset=UTF-8")
    @POST("GetbindPriority")
    fun getPriority(@Body praments: Praments?): Call<Responses?>?

    // 10 category
    @Headers("Content-Type: application/json;charset=UTF-8")
    @POST("GetbindCategory")
    fun getCategory(@Body praments: Praments?): Call<Responses?>?


    // 11 assing to
    @Headers("Content-Type: application/json;charset=UTF-8")
    @POST("GetbindAssignTo")
    fun getAssingto(@Body praments: Praments?): Call<Responses?>?

    // 12 ComplaintNature
    @Headers("Content-Type: application/json;charset=UTF-8")
    @POST("GetbindComplaintNature")
    fun getComplaintNature(@Body praments: Praments?): Call<Responses?>?

    // 13 NatureInsUpdDel
    @Headers("Content-Type: application/json;charset=UTF-8")
    @POST("GetbindNatureInsUpdDel")
    fun addnature(@Body praments: Praments?): Call<Responses?>?

    // 14 add AssignComplaint
    @Headers("Content-Type: application/json;charset=UTF-8")
    @POST("GetbindAssignComplaint")
    fun addAssignComplaint(@Body praments: Praments?): Call<Responses?>?

    // 15 CompPost
    @Headers("Content-Type: application/json;charset=UTF-8")
    @POST("GetCompPost")
    fun getCompPost(@Body praments: Praments?): Call<Responses?>?


    // 16 Image Save


    @Multipart
    @POST("GetGrPubUserImg")
    fun saveImage(

        @Part("PPOST_ID") PPOST_ID: RequestBody,
        @Part("PROW_ID") PROW_ID: RequestBody,
        @Part("ShowMode") ShowMode: RequestBody,
        @Part("PX1") PX1: RequestBody,
        @Part("PX2") PX2: RequestBody,
        @Part("PX3") PX3: RequestBody,
        @Part("PX4") PX4: RequestBody,
        @Part("PX5") PX5: RequestBody,
        @Part("PX6") PX6: RequestBody,
        @Part("PX7") PX7: RequestBody,
        @Part("PX8") PX8: RequestBody,
        @Part("PX9") PX9: RequestBody,
        @Part("PX10") PX10: RequestBody,
        @Part("PIP") PIP: RequestBody,
        @Part("PCOMP_CODE") PCOMP_CODE: RequestBody?,
        @Part("PCENT_CODE") PCENT_CODE: RequestBody?,
        @Part("PUSERID") PUSERID: RequestBody?,
        @Part("PCOMPLAINT_ID") PCOMPLAINT_ID: RequestBody?,
        @Part("PTYPE") PTYPE: RequestBody?,
        @Part("FileName") filename1: RequestBody?,
        @Part profile_photo: MultipartBody.Part
    ): Call<ResponseImage?>?



    // 17  Status
    @Headers("Content-Type: application/json;charset=UTF-8")
    @POST("GetBindStatus")
    fun getStatus(@Body praments: Praments?): Call<Responses?>?


    //18 Equipment

    @Headers("Content-Type: application/json;charset=UTF-8")
    @POST("GetBindEquipment")
    fun getEquipment(@Body praments: Praments?): Call<Responses?>?

    //19 Failure

    @Headers("Content-Type: application/json;charset=UTF-8")
    @POST("GetBindFailure")
    fun getFailure(@Body praments: Praments?): Call<Responses?>?

    //20  BindItem


    @Headers("Content-Type: application/json;charset=UTF-8")
    @POST("GetBindItem")
    fun getBindItem(@Body praments: Praments?): Call<Responses?>?


    // 21 BindVendor

    @Headers("Content-Type: application/json;charset=UTF-8")
    @POST("GetBindVendor")
    fun getBindVendor(@Body praments: Praments?): Call<Responses?>?

    // 22 GetBindCompPart

    @Headers("Content-Type: application/json;charset=UTF-8")
    @POST("GetBindCompPart")
    fun getBindCompPart(@Body praments: Praments?): Call<Responses?>?
}