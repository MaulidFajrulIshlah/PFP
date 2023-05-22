package com.geminiboy.pfp.model.sliders


import com.google.gson.annotations.SerializedName

data class ResponseSlidersItem(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: String
)