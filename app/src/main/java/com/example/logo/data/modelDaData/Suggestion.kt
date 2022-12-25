package com.example.logo.data.modelDaData


import com.google.gson.annotations.SerializedName

data class  Suggestion(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("unrestricted_value")
    val unrestrictedValue: String,
    @SerializedName("value")
    val value: String
)