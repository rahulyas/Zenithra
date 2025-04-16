package com.rahul.zenithra.domain.repository

import com.rahul.zenithra.data.model.SessionEntity
import com.rahul.zenithra.data.model.UserEntity
import com.rahul.zenithra.domain.model.User


interface UserRepository {
    suspend fun getUserByEmail(email: String): User?
    // other functions like addUser(), getUsers(), etc.
    suspend fun signUp(userEntity : UserEntity)

    suspend fun saveSession(userId: Int)
    suspend fun getSession(): SessionEntity?
    suspend fun clearSession()
}