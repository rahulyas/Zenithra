package com.rahul.zenithra.domain.usecase

import com.rahul.zenithra.data.model.UserEntity
import com.rahul.zenithra.domain.repository.UserRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(userEntity : UserEntity) {
        userRepository.signUp(userEntity)
    }
}
