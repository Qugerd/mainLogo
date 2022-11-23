package com.example.logo.data.modelProductDetails


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("id")
    val id: String,

    @SerializedName("article_number")
    val articleNumber: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("slug")
    val slug: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("price")
    val price: String,

    @SerializedName("discount_price")
    val discountPrice: String,

    @SerializedName("is_sale")
    val isSale: Boolean,

    @SerializedName("is_new")
    val isNew: Boolean,

    @SerializedName("category")
    val category: Category,

    @SerializedName("attributes")
    val attributes: List<Attribute>,

    @SerializedName("colors")
    val colors: List<Color>,

    @SerializedName("size")
    val size: List<Size>,

    @SerializedName("images")
    val images: List<Image>,

    @SerializedName("brand")
    val brand: Brand
)