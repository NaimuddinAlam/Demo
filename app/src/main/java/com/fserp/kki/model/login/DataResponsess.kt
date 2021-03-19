package com.fserp.kki.model.login


import com.google.gson.annotations.SerializedName

data class DataResponsess(
    @SerializedName("data")
    val `data`: LoginData,
    @SerializedName("error")
    val error: Any,
    @SerializedName("isSuccess")
    val isSuccess: Boolean
)