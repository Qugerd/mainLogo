package com.example.logo.data.model

import com.google.gson.annotations.SerializedName

data class Option(
    @SerializedName("value")
    val value: String,

    @SerializedName("isSelected")
    val isSelected: Boolean,

    @SerializedName("productCount")
    val productCount: Int
)