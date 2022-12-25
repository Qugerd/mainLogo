package com.example.logo.data.modelCart


import com.google.gson.annotations.SerializedName

data class ProductX(
    @SerializedName("article_number")
    val articleNumber: String,
    @SerializedName("attribute")
    val attribute: List<Attribute>,
    @SerializedName("brand")
    val brand: Brand,
    @SerializedName("category")
    val category: Category,
    @SerializedName("colors")
    val colors: List<Color>,
    @SerializedName("description")
    val description: String,
    @SerializedName("discount_price")
    val discountPrice: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("images")
    val images: List<ImageX>,
    @SerializedName("is_new")
    val isNew: Boolean,
    @SerializedName("is_sale")
    val isSale: Boolean,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("sizes")
    val sizes: List<Size>,
    @SerializedName("slug")
    val slug: String
)