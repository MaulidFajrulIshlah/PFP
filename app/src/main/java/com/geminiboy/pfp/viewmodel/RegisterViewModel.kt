package com.geminiboy.pfp.viewmodel

import androidx.lifecycle.ViewModel
import com.geminiboy.pfp.data.remote.service.APIService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val api: APIService): ViewModel() {
    

}