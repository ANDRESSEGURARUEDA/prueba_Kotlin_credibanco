package com.example.pruebakotlincredibanco.ui.gallery

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pruebakotlincredibanco.domain.GetTransactionUseCase
import com.example.pruebakotlincredibanco.domain.model.TransactionDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val getTransactionUseCase: GetTransactionUseCase,
) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Annulment"
    }
    val text: LiveData<String> = _text

    private val _transactionsLiveData = MutableLiveData<List<TransactionDomain>?>()
    val transactionsLiveData: LiveData<List<TransactionDomain>?>
        get() = _transactionsLiveData

    private val _resultado = MutableLiveData<String>()
    val mensajeResultado: LiveData<String>
        get() = _resultado


    fun onCreate(receiptId: String) {
        viewModelScope.launch {
            val result = getTransactionUseCase(receiptId)
            if (!result.isNullOrEmpty()) {
                _transactionsLiveData.postValue(result!!)
                _resultado.postValue("Consulta exitosa")
            }else{
                _resultado.postValue("Tu transaccion no existe")
            }
        }
    }

    fun observeTransactions(): LiveData<List<TransactionDomain>?> {
        return transactionsLiveData
    }

    @SuppressLint("SuspiciousIndentation")
    fun annulmentTransaction(transactionDomain: TransactionDomain) {
        viewModelScope.launch(Dispatchers.IO) {
            if (transactionDomain != null  && transactionDomain.statusDescription == "Aprobada") {
                transactionDomain.statusDescription = "Anulada"
                val updatedTransaction = transactionDomain
                    getTransactionUseCase.annulmenteTransaction(updatedTransaction)
                    _transactionsLiveData.postValue(listOf(updatedTransaction))
                    _resultado.postValue("Transaccion anulada exitosamente")
                } else  {
                    _resultado.postValue("Tu transccion ya se encuentra anulada")
                }
            }
        }

}