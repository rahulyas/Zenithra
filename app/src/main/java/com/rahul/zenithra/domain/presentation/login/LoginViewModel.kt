package com.rahul.zenithra.domain.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rahul.zenithra.domain.presentation.login.component.LoginUiEvent
import com.rahul.zenithra.domain.usecase.LoginUserUseCase
import com.rahul.zenithra.domain.usecase.SaveSessionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val passwordVisible: Boolean = false
) {
    val canSubmit: Boolean
        get() = email.isNotBlank() && password.isNotBlank()
}


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUserUseCase: LoginUserUseCase,
    private val saveSessionUseCase: SaveSessionUseCase
) : ViewModel() {
    private val _uiEvent = Channel<LoginUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    var uiState by mutableStateOf(LoginUiState())
        private set

    fun onEmailChanged(email: String) {
        uiState = uiState.copy(email = email)
    }

    fun onPasswordChanged(password: String) {
        uiState = uiState.copy(password = password)
    }

    fun togglePasswordVisibility() {
        uiState = uiState.copy(passwordVisible = !uiState.passwordVisible)
    }

    fun signIn(onSuccess: () -> Unit) {
        viewModelScope.launch {
            val result = loginUserUseCase(uiState.email, uiState.password)
            result.let {
                if (it.first) {
                    it.second?.let {user->
                        saveSessionUseCase(user.id)
                    }
                    onSuccess()
                }
                else {
                    _uiEvent.send(LoginUiEvent.ShowToast("Invalid credentials"))
                    // Show error (you can extend uiState to include error message)
                }
            }
        }
    }
}
