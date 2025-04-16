package com.rahul.zenithra.domain.usecase

import com.rahul.zenithra.domain.repository.UserRepository
import javax.inject.Inject

class ClearSessionUseCase @Inject constructor (private val repository: UserRepository) {
    suspend operator fun invoke() = repository.clearSession()
}