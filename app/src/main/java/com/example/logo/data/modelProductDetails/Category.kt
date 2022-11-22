package com.example.logo.data.modelProductDetails


import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("parent_id")
    val parentId: String,
    @SerializedName("slug")
    val slug: String
)