package com.example.logo.data.modelMainPage


import com.google.gson.annotations.SerializedName

data class LandingSlide(
    @SerializedName("landing_image")
    val landingImage: String,
    @SerializedName("subtitle")
    val subtitle: String
)