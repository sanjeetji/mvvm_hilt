package com.sanjeet.simpletrancsaction.view.Home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanjeet.simpletrancsaction.common.Resource
import com.sanjeet.simpletrancsaction.domain.usecases.HomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeUseCase: HomeUseCase) : ViewModel() {

    private val _accounts = MutableStateFlow<HomeState>(HomeState())
    val accounts: StateFlow<HomeState> = _accounts

    private val _transaction = MutableStateFlow<TransactionState>(TransactionState())
    val transaction: StateFlow<TransactionState> = _transaction


    fun getAccounts() {
        homeUseCase.invoke().onEach {
            when (it) {
                is Resource.Loading -> {
                    _accounts.value = HomeState(isLoading = true)
                }
                is Resource.Error -> {
                    _accounts.value = HomeState(error = it.message ?: "")
                }
                is Resource.Success -> {
                    _accounts.value = HomeState(data = it.data)
                }
                else -> {
                    _accounts.value = HomeState(error = it.message ?: "")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getTransactions(accountId: String) {
        homeUseCase.invoke(accountId).onEach {
            when (it) {
                is Resource.Loading -> {
                    _transaction.value = TransactionState(isLoading = true)
                }
                is Resource.Error -> {
                    _transaction.value = TransactionState(error = it.message ?: "")
                }
                is Resource.Success -> {
                    _transaction.value = TransactionState(data = it.data)
                }
                else -> {
                    _transaction.value = TransactionState(error = it.message ?: "")
                }
            }
        }.launchIn(viewModelScope)
    }

}