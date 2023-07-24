package ru.ponomarchukpn.insittask.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import ru.ponomarchukpn.insittask.domain.entity.UserEntity
import ru.ponomarchukpn.insittask.domain.usecases.LoginUseCase
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _loginState = MutableSharedFlow<Boolean>(extraBufferCapacity = 1)
    val loginState = _loginState.asSharedFlow()

    fun onLoginPressed(username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = loginUseCase.login(UserEntity(username))
            _loginState.tryEmit(result)
        }
    }
}