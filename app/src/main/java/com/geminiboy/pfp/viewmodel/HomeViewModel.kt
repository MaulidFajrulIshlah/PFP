package com.geminiboy.pfp.viewmodel

import android.support.v4.os.IResultReceiver._Parcel
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geminiboy.pfp.data.remote.service.APIService
import com.geminiboy.pfp.model.category.ResponseCategoryItem
import com.geminiboy.pfp.model.news.ResponseNewsItem
import com.geminiboy.pfp.model.product.ResponseProductItem
import com.geminiboy.pfp.model.sliders.ResponseSlidersItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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

    private val _sliders = MutableLiveData<List<ResponseSlidersItem>>()
    val sliders: LiveData<List<ResponseSlidersItem>> = _sliders

    private val _toCateg: MutableLiveData<List<ResponseCategoryItem>> = MutableLiveData()
    val toCateg: LiveData<List<ResponseCategoryItem>> get() = _toCateg


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

    fun setToCategory() = viewModelScope.launch {
        val response = api.getCategory()
        try {
            _toCateg.postValue(response)

        }catch (e : Exception){
            Log.e("error", e.message!!)
        }
    }


    fun getSliders() = viewModelScope.launch(Dispatchers.IO) {
        try {
            val response = api.getSliders()
            _sliders.postValue(response)
        }catch (e: Exception){
            throw e
        }
    }

}