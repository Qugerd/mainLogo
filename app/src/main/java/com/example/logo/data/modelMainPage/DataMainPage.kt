package com.example.logo.data.modelMainPage

import com.google.gson.annotations.SerializedName

data class DataMainPage(
    @SerializedName("categories")
    val categories: List<Category>,
    @SerializedName("landing_slide")
    val landingSlide: LandingSlide,
    @SerializedName("new_products")
    val newProducts: List<NewProduct>,
    @SerializedName("sale_products")
    val saleProducts: List<SaleProduct>
)
