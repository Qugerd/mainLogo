package com.example.logo.data.model

import com.google.gson.annotations.SerializedName

data class Filter(
    @SerializedName("key")
    val key: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("type")
    val type: Int,

    @SerializedName("options")
    val options: List<Option>
)