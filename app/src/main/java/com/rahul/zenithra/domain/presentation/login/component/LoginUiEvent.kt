package com.rahul.zenithra.domain.presentation.login.component

sealed class LoginUiEvent {
    data class ShowToast(val message: String) :LoginUiEvent()
}