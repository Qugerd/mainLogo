package com.example.logo

import com.example.logo.data.Data
import com.google.gson.annotations.SerializedName

data class Categor(
    @SerializedName("data")
    val data: List<Data>
)
