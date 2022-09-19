package com.sanjeet.simpletrancsaction.view.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanjeet.simpletrancsaction.common.Resource
import com.sanjeet.simpletrancsaction.domain.usecases.UserLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UserLoginViewModel @Inject constructor(private val userLoginUseCase: UserLoginUseCase) :
    ViewModel() {


    private val _userLogin = MutableStateFlow<UserLoginState>(UserLoginState())
    val userLogin: StateFlow<UserLoginState> = _userLogin

    fun userLogin(username: String, password: String) {
        userLoginUseCase.invoke(username, password).onEach {
            when (it) {
                is Resource.Loading -> {
                    _userLogin.value = UserLoginState(isLoading = true)
                }
                is Resource.Error -> {
                    _userLogin.value = UserLoginState(error = it.message ?: "")
                }
                is Resource.LoginSuccess -> {
                    _userLogin.value = UserLoginState(data = it.data)
                }
                else -> {
                    _userLogin.value = UserLoginState(error = it.message ?: "")
                }
            }
        }.launchIn(viewModelScope)
    }
}