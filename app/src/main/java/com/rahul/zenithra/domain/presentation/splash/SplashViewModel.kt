package com.rahul.zenithra.domain.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rahul.zenithra.domain.repository.UserRepository
import com.rahul.zenithra.domain.usecase.GetSessionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getSessionUseCase: GetSessionUseCase
): ViewModel() {

    private val _isLoggedIn = MutableStateFlow<Boolean?>(null)
    val isLoggedIn = _isLoggedIn.asStateFlow()

    init {
        viewModelScope.launch {
            delay(1500) // Optional delay
            val session = getSessionUseCase.invoke()
            _isLoggedIn.value = session?.isLoggedIn == true
        }
    }
}
