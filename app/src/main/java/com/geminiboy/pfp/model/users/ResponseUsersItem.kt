package com.geminiboy.pfp.model.users


import com.google.gson.annotations.SerializedName

data class ResponseUsersItem(
    @SerializedName("email")
    val email: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("password")
    val password: String
)