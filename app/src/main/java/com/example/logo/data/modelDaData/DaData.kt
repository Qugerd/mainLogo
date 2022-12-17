package com.example.logo.data.modelDaData


import com.google.gson.annotations.SerializedName

data class DaData(
    @SerializedName("suggestions")
    val suggestions: List<Suggestion>
)