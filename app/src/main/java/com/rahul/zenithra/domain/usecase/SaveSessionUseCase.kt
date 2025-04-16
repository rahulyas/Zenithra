package com.rahul.zenithra.domain.usecase

import com.rahul.zenithra.domain.repository.UserRepository
import javax.inject.Inject

class SaveSessionUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(userId: Int) = repository.saveSession(userId)

}
