package com.geminiboy.pfp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geminiboy.pfp.data.remote.service.APIService
import com.geminiboy.pfp.model.transaksi_history.ResponseTransactionHistoryItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class TransactionHistoryViewModel @Inject constructor(private val apiService: APIService): ViewModel() {
    private val _transHistory = MutableLiveData<List<ResponseTransactionHistoryItem>>()
    val transHistory: LiveData<List<ResponseTransactionHistoryItem>> = _transHistory

    fun getTransHistory() = runBlocking {
        try {
            val response = apiService.getUserTransactionHistory(6)
            _transHistory.postValue(response)
        }catch (e: Exception){
            throw e
        }
    }
}