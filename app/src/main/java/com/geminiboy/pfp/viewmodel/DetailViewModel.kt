package com.geminiboy.pfp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geminiboy.pfp.data.remote.service.APIService
import com.geminiboy.pfp.model.product.ResponseProductItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val api : APIService): ViewModel() {

    private val _detailProduct: MutableLiveData<ResponseProductItem> = MutableLiveData()
    val detailProduct: LiveData<ResponseProductItem> get() = _detailProduct

    fun setDetailProduct() = viewModelScope.launch {
        val response = api.getProductById(1, 1)
        try {
            _detailProduct.postValue(response)
        }catch(e: Exception) {
            Log.e("error", e.message!!)
        }
    }


}