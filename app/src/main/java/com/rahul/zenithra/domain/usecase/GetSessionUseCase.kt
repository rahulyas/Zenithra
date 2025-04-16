package com.rahul.zenithra.domain.usecase

import com.rahul.zenithra.data.model.SessionEntity
import com.rahul.zenithra.domain.repository.UserRepository
import javax.inject.Inject

class GetSessionUseCase@Inject constructor(private val repository: UserRepository) {
    suspend operator fun invoke(): SessionEntity? = repository.getSession()
}
