package com.example.logo.data.modelCart


import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: List<ImageX>,
    @SerializedName("name")
    val name: String,
    @SerializedName("parent_id")
    val parentId: Int,
    @SerializedName("slug")
    val slug: String
)