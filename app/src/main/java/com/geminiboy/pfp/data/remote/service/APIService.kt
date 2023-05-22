package com.geminiboy.pfp.data.remote.service

import com.geminiboy.pfp.model.cart.ResponseCart
import com.geminiboy.pfp.model.category.ResponseCategory
import com.geminiboy.pfp.model.favourite.ResponseFavourite
import com.geminiboy.pfp.model.news.ResponseNews
import com.geminiboy.pfp.model.product.ResponseProduct
import com.geminiboy.pfp.model.sliders.ResponseSliders
import com.geminiboy.pfp.model.transaksi_history.ResponseTransactionHistory
import com.geminiboy.pfp.model.users.ResponseUsers
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface APIService {
    //Users
    @GET("users/{id}")
    fun getUserById(@Path("id") id: Int): ResponseUsers

    @POST("users")
    fun postUser(): ResponseUsers

    @PUT("users/{id}")
    fun putUserById(@Path("id") id: Int): ResponseUsers

    //Transaction History
    @GET("users/{id}/transhistory")
    fun getUserTransactionHistory(@Path("id") id: Int): ResponseTransactionHistory

    @POST("users/{id}/transhistory")
    fun postTransactionHistory(@Path("id") id: Int): ResponseUsers

    @DELETE("users/{id}/transhistory/{id_trans}")
    fun deleteTransactionHistory(
        @Path("id") id: Int,
        @Path("id_trans") id_trans: Int
    ): ResponseTransactionHistory

    //Cart
    @GET("users/{id}/cart")
    fun getUserCart(@Path("id") id: Int): ResponseCart

    @POST("users/{id}/cart")
    fun postCart(@Path("id") id: Int): ResponseCart

    @DELETE("users/{id}/cart/{id_cart}")
    fun deleteCart(
        @Path("id") id: Int,
        @Path("id_cart") id_cart: Int
    ): ResponseCart

    //Favourite
    @GET("users/{id}/favourite")
    fun getUserFavourite(@Path("id") id: Int): ResponseFavourite

    @POST("users/{id}/favourite")
    fun postFavourite(@Path("id") id: Int): ResponseFavourite

    @DELETE("users/{id}/cart/{id_fav}")
    fun deleteFavourite(
        @Path("id") id: Int,
        @Path("id_fav") id_fav: Int
    ): ResponseFavourite

    //News Update
    @GET("news_update")
    fun getNewsUpdate(): ResponseNews

    //Category Product
    @GET("category_product")
    fun getCategory(): ResponseCategory

    @GET("category_product/{id}")
    fun getCategoryById(@Path("id") id: Int): ResponseCategory

    //Product
    @GET("category_product/{id}/products")
    fun getProduct(@Path("id") id: Int): ResponseProduct

    @GET("category_product/{id}/products/{id_product}")
    fun getProductById(
        @Path("id") id: Int,
        @Path("id_product") id_product: Int
    ): ResponseProduct

    //Sliders
    @GET("sliders")
    fun getSliders(): ResponseSliders
}