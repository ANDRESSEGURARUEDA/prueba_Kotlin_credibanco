package com.example.pruebakotlincredibanco.ui.home

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pruebacredibanco.network.TransactionModel
import com.example.pruebakotlincredibanco.domain.AuthorizationTransactionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val authorizationTransactionUseCase: AuthorizationTransactionUseCase,
) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Authorization"
    }
    val text: LiveData<String> = _text

    private val _resultado = MutableLiveData<String>()
    val mensajeResultado: LiveData<String>
        get() = _resultado

    @SuppressLint("SuspiciousIndentation")
    fun onCreate(transactionModel: TransactionModel) {
        viewModelScope.launch {
            val result = authorizationTransactionUseCase(transactionModel)
            if (result == null) {
                _resultado.postValue("Transaccion declinada,comunicate con tu banco")
            } else if (result.statusDescription.equals("Aprobada")) {
                _resultado.postValue("Transacción creada exitosamente")
            }
        }
    }
}
