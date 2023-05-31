package com.geminiboy.pfp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geminiboy.pfp.data.remote.service.APIService
import com.geminiboy.pfp.model.favourite.ResponseFavouriteItem
import com.geminiboy.pfp.model.product.ResponseProductItem
import com.geminiboy.pfp.wrapper.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val apiFav : APIService): ViewModel(){

    private val _favorite: MutableLiveData<List<ResponseFavouriteItem>> = MutableLiveData()
    val favorite: LiveData<List<ResponseFavouriteItem>> get() = _favorite


    fun setFavoriteList() = viewModelScope.launch {
        val response = apiFav.getUserFavourite(1)
        try {
            _favorite.postValue(response)
        }catch(e: Exception) {
            Log.e("error", e.message!!)
        }

    }


//    fun setFavoriteList(id : Int) = viewModelScope.launch {
//        try {
//            _favorite.postValue(Resource.Loading())
//
//            val response = apiFav.getUserFavourite(id)
//            _favorite.postValue(Resource.Success(response))
//
//        } catch(e: Exception) {
//            _favorite.postValue(Resource.Error(e.message!!))
//            Log.e("error", e.message!!)
//        }
//    }


}