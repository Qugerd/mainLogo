package com.example.logo.data.model

import com.google.gson.annotations.SerializedName

data class Images(
    @SerializedName("id")
    val id: String,

    @SerializedName("path")
    val path: String
)