package com.example.logo.data.postCheckSmsCode


import com.google.gson.annotations.SerializedName

data class CheckSms(
    @SerializedName("token")
    val token: String
)