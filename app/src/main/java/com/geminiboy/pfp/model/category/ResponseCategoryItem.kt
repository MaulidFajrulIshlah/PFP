package com.geminiboy.pfp.model.category


import com.google.gson.annotations.SerializedName

data class ResponseCategoryItem(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String
)