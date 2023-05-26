package com.geminiboy.pfp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geminiboy.pfp.data.remote.service.APIService
import com.geminiboy.pfp.model.users.ResponseUsersItem
import com.geminiboy.pfp.model.users.UserBody
import com.geminiboy.pfp.wrapper.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.Flow
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val api: APIService): ViewModel() {
    private val _registerResult: MutableLiveData<Resource<ResponseUsersItem>> = MutableLiveData()
    val registerResult: LiveData<Resource<ResponseUsersItem>> get() = _registerResult

    fun postUser(name: String, email: String, password: String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            _registerResult.postValue(Resource.Loading())
            val response = api.postUser(UserBody(name, email, password))
            _registerResult.postValue(Resource.Success(response))
        }catch (e: Exception){
            _registerResult.postValue(Resource.Error(e.message!!))
            throw e
        }
    }

    suspend fun isUsernameTaken(username: String): Boolean {
        try {
            val response = api.getUser()
            for(user in response){
                if(user.name == username) return true
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }

    suspend fun isEmailTaken(email: String): Boolean {
        try {
            val response = api.getUser()
            for(user in response){
                if(user.email == email) return true
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }
}