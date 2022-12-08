package com.example.logo.data.modelMainPage


import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: List<Image>,
    @SerializedName("name")
    val name: String,
    @SerializedName("parent_id")
    val parentId: String,
    @SerializedName("slug")
    val slug: String
)