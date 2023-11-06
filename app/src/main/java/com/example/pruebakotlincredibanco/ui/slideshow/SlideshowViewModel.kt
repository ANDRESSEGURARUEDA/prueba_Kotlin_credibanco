package com.example.pruebakotlincredibanco.ui.slideshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pruebakotlincredibanco.domain.GeAllTransactions
import com.example.pruebakotlincredibanco.domain.model.TransactionDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SlideshowViewModel @Inject constructor(private val getAllTransactions: GeAllTransactions) :
    ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Your Transactions"
    }
    val text: LiveData<String> = _text
    private val _transactionsLiveData = MutableLiveData<List<TransactionDomain>?>()
    val transactionsLiveData: LiveData<List<TransactionDomain>?>
        get() = _transactionsLiveData

    fun onCreate() {
        viewModelScope.launch {
            val result = getAllTransactions()
            if (!result.isNullOrEmpty()) {
                _transactionsLiveData.postValue(result!!)
            }
        }
    }

    fun observeTransactions(): LiveData<List<TransactionDomain>?> {
        return transactionsLiveData
    }

}