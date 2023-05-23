package com.geminiboy.pfp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geminiboy.pfp.data.remote.service.APIService
import com.geminiboy.pfp.model.category.ResponseCategoryItem
import com.geminiboy.pfp.model.news.ResponseNewsItem
import com.geminiboy.pfp.model.product.ResponseProductItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val api : APIService

    ): ViewModel() {

    private val _belanjaan: MutableLiveData<List<ResponseNewsItem>> = MutableLiveData()
    val belanjaan: LiveData<List<ResponseNewsItem>> get() = _belanjaan

    private val _product: MutableLiveData<List<ResponseProductItem>> = MutableLiveData()
    val product: LiveData<List<ResponseProductItem>> get() = _product





    fun setNewsUpdate() = viewModelScope.launch {
        val response = api.getNewsUpdate()
        try {
            _belanjaan.postValue(response)
        }catch(e: Exception) {
            Log.e("error", e.message!!)
        }

    }


    fun setProduct() = viewModelScope.launch {
        val response = api.getProduct(1)
        try {
            _product.postValue(response)
        }catch(e: Exception) {
            Log.e("error", e.message!!)
        }

    }




}