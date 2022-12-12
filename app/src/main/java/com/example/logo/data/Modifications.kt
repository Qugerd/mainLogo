package com.example.logo.data


import com.google.gson.annotations.SerializedName

data class Modifications(
    @SerializedName("modifications")
    val modifications: List<Modification>
)