package com.example.logo.data.modelCategoryList

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("slug") val slug: String,
    @SerializedName("parent_id") val parent_id: Int,

)
