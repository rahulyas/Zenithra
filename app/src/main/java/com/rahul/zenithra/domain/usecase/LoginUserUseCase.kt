package com.rahul.zenithra.domain.usecase

import com.rahul.zenithra.domain.model.User
import com.rahul.zenithra.domain.repository.UserRepository
import javax.inject.Inject

class LoginUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(email: String, password: String): Pair<Boolean, User?> {
        val user = userRepository.getUserByEmail(email)
        return Pair(user != null && user.password == password, user)
    }
}