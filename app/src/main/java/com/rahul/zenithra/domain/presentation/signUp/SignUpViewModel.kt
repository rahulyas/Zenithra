package com.rahul.zenithra.domain.presentation.signUp

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rahul.zenithra.data.model.UserEntity
import com.rahul.zenithra.domain.presentation.signUp.component.SignUpUiEvent
import com.rahul.zenithra.domain.usecase.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SignUpUiState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val passwordVisible: Boolean = false,
    val canSubmit: Boolean = false
)


@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    var uiState by mutableStateOf(SignUpUiState())
        private set
    private val _uiEvent = Channel<SignUpUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onNameChanged(newName: String) {
        uiState = uiState.copy(name = newName)
        validateInputs()
    }

    fun onEmailChanged(newEmail: String) {
        uiState = uiState.copy(email = newEmail)
        validateInputs()
    }

    fun onPasswordChanged(newPassword: String) {
        uiState = uiState.copy(password = newPassword)
        validateInputs()
    }

    fun togglePasswordVisibility() {
        uiState = uiState.copy(passwordVisible = !uiState.passwordVisible)
    }

    private fun validateInputs() {
        val nameValid = uiState.name.isNotBlank()
        val emailValid = uiState.email.contains("@")
        val passwordValid = uiState.password.length >= 6

        val isValid = nameValid && emailValid && passwordValid
        uiState = uiState.copy(canSubmit = isValid)

        viewModelScope.launch {
            when {
                !nameValid -> _uiEvent.send(SignUpUiEvent.ShowToast("Name cannot be empty"))
                !emailValid -> _uiEvent.send(SignUpUiEvent.ShowToast("Invalid email address"))
                !passwordValid -> _uiEvent.send(SignUpUiEvent.ShowToast("Password must be at least 6 characters"))
            }
        }
    }

    fun signUp(onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                signUpUseCase(
                    UserEntity(
                        name = uiState.name,
                        email = uiState.email,
                        password = uiState.password
                    )
                )
                onSuccess()
            } catch (e: Exception) {
                // Handle error (e.g., show a toast/snackbar via event)
                Log.e("SignUpViewModel", "Sign-up failed: ${e.message}")
            }
        }
    }
}
