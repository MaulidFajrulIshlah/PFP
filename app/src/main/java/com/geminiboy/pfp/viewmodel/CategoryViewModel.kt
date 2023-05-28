package com.geminiboy.pfp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geminiboy.pfp.data.remote.service.APIService
import com.geminiboy.pfp.model.category.ResponseCategoryItem
import com.geminiboy.pfp.model.product.ResponseProductItem
import com.geminiboy.pfp.wrapper.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CategoryViewModel @Inject constructor(private val api : APIService): ViewModel(){

    private val _listCategory: MutableLiveData<Resource<ResponseCategoryItem>> = MutableLiveData()
    val listCategory: LiveData<Resource<ResponseCategoryItem>> get() = _listCategory


    fun setCategoryList(id: Int) = viewModelScope.launch {
        try {
            _listCategory.postValue(Resource.Loading())

            val response = api.getCategoryById(id)
            _listCategory.postValue(Resource.Success(response))

        } catch(e: Exception) {
            _listCategory.postValue(Resource.Error(e.message!!))
            Log.e("error", e.message!!)
        }
    }


}