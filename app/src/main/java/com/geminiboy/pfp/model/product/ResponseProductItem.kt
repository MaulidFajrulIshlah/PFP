package com.geminiboy.pfp.model.product


import com.google.gson.annotations.SerializedName

data class ResponseProductItem(
    @SerializedName("category_productId")
    val categoryProductId: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("id_product")
    val idProduct: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("product_image")
    val productImage: String
)