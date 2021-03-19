package com.fserp.kki.model.login


import com.google.gson.annotations.SerializedName

data class LoginData(
    @SerializedName("COMP_CODE")
    val cOMPCODE: String,
    @SerializedName("EMAIL")
    val eMAIL: String,
    @SerializedName("LOGIN_DATETIME")
    val lOGINDATETIME: String,
    @SerializedName("MOBILE")
    val mOBILE: String,
    @SerializedName("RES")
    val rES: String,
    @SerializedName("TYPE")
    val tYPE: String,
    @SerializedName("USERID")
    val uSERID: String,
    @SerializedName("USERNAME")
    val uSERNAME: String,
    @SerializedName("CENT_CODE")
      val CENT_CODE: String
)