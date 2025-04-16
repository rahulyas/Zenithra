package com.rahul.zenithra.domain.presentation.signUp.component

sealed class SignUpUiEvent {
    data class ShowToast(val message: String) : SignUpUiEvent()
}
