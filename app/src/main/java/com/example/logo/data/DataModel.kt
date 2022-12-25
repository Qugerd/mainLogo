package com.example.logo.data


import com.google.gson.annotations.SerializedName

data class DataModel(
    @SerializedName("query")
    val query: String,

    @SerializedName("count")
    val count: Int
)