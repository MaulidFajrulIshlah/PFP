package com.geminiboy.pfp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geminiboy.pfp.data.remote.service.APIService
import com.geminiboy.pfp.model.users.ResponseUsersItem
import com.geminiboy.pfp.model.users.SignInBody
import com.geminiboy.pfp.wrapper.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val apiService: APIService): ViewModel() {
    private val _loginResult: MutableLiveData<Resource<ResponseUsersItem>> = MutableLiveData()
    val loginResult: LiveData<Resource<ResponseUsersItem>> get() = _loginResult

    fun authLogin(signInBody: SignInBody) = viewModelScope.launch {
        _loginResult.postValue(Resource.Loading())
        try {
            val response = apiService.getUser()
            val user = response.find { it.email == signInBody.email && it.password == signInBody.password }
            if (user != null) {
                _loginResult.postValue(Resource.Success(user))
            }else{
                _loginResult.postValue(Resource.Error("Email or Password is invalid"))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            _loginResult.postValue(Resource.Error("Terjadi kesalahan saat melakukan autentikasi"))
        }
    }

}