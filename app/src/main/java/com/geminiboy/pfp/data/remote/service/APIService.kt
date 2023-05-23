package com.geminiboy.pfp.data.remote.service

import com.geminiboy.pfp.model.cart.ResponseCart
import com.geminiboy.pfp.model.category.ResponseCategory
import com.geminiboy.pfp.model.favourite.ResponseFavourite
import com.geminiboy.pfp.model.news.ResponseNews
import com.geminiboy.pfp.model.news.ResponseNewsItem
import com.geminiboy.pfp.model.product.ResponseProduct
import com.geminiboy.pfp.model.product.ResponseProductItem
import com.geminiboy.pfp.model.sliders.ResponseSliders
import com.geminiboy.pfp.model.transaksi_history.ResponseTransactionHistory
import com.geminiboy.pfp.model.users.ResponseUsers
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface APIService {
    //Users
    @GET("users/{id}")
    suspend fun getUserById(@Path("id") id: Int): ResponseUsers

    @POST("users")
    suspend fun postUser(): ResponseUsers

    @PUT("users/{id}")
    suspend fun putUserById(@Path("id") id: Int): ResponseUsers

    //Transaction History
    @GET("users/{id}/transhistory")
    suspend fun getUserTransactionHistory(@Path("id") id: Int): ResponseTransactionHistory

    @POST("users/{id}/transhistory")
    suspend fun postTransactionHistory(@Path("id") id: Int): ResponseUsers

    @DELETE("users/{id}/transhistory/{id_trans}")
    suspend fun deleteTransactionHistory(
        @Path("id") id: Int,
        @Path("id_trans") id_trans: Int
    ): ResponseTransactionHistory

    //Cart
    @GET("users/{id}/cart")
    suspend fun getUserCart(@Path("id") id: Int): ResponseCart

    @POST("users/{id}/cart")
    suspend fun postCart(@Path("id") id: Int): ResponseCart

    @DELETE("users/{id}/cart/{id_cart}")
    suspend fun deleteCart(
        @Path("id") id: Int,
        @Path("id_cart") id_cart: Int
    ): ResponseCart

    //Favourite
    @GET("users/{id}/favourite")
    suspend fun getUserFavourite(@Path("id") id: Int): ResponseFavourite

    @POST("users/{id}/favourite")
    suspend fun postFavourite(@Path("id") id: Int): ResponseFavourite

    @DELETE("users/{id}/cart/{id_fav}")
    suspend fun deleteFavourite(
        @Path("id") id: Int,
        @Path("id_fav") id_fav: Int
    ): ResponseFavourite

    //News Update
    @GET("news_update")
    suspend fun getNewsUpdate(): ResponseNews

    //Category Product
    @GET("category_product")
    suspend fun getCategory(): ResponseCategory

    @GET("category_product/{id}")
    suspend fun getCategoryById(@Path("id") id: Int): ResponseCategory

    //Product
    @GET("category_product/{id}/products")
    suspend fun getProduct(@Path("id") id: Int): ResponseProduct

    @GET("category_product/{id}/products/{id_product}")
    suspend fun getProductById(
        @Path("id") id: Int,
        @Path("id_product") id_product: Int
    ): ResponseProductItem

    //Sliders
    @GET("sliders")
    suspend fun getSliders(): ResponseSliders
}