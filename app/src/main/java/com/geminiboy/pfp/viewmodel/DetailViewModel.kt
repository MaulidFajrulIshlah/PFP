package com.geminiboy.pfp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geminiboy.pfp.data.remote.service.APIService
import com.geminiboy.pfp.model.product.ResponseProductItem
import com.geminiboy.pfp.wrapper.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val api : APIService): ViewModel() {

    private val _detailProduct: MutableLiveData<Resource<ResponseProductItem>> = MutableLiveData()
    val detailProduct: LiveData<Resource<ResponseProductItem>> get() = _detailProduct

    fun setDetailProduct(id_product : Int) = viewModelScope.launch {
        try {
            _detailProduct.postValue(Resource.Loading())

            val response = api.getProductById(1, id_product)
            _detailProduct.postValue(Resource.Success(response))

        } catch(e: Exception) {
            _detailProduct.postValue(Resource.Error(e.message!!))
            Log.e("error", e.message!!)
        }
    }
}