package com.rahul.zenithra.domain.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rahul.zenithra.domain.usecase.ClearSessionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val clearSessionUseCase: ClearSessionUseCase
) : ViewModel() {

    fun onLogoutClicked(onComplete: () -> Unit) {
        viewModelScope.launch {
            clearSessionUseCase()
            onComplete()
        }
    }
}