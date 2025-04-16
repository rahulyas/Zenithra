package com.rahul.zenithra.data.repository

import com.rahul.zenithra.data.dao.SessionDao
import com.rahul.zenithra.domain.repository.UserRepository
import com.rahul.zenithra.data.dao.UserDao
import com.rahul.zenithra.data.model.SessionEntity
import com.rahul.zenithra.data.model.UserEntity
import com.rahul.zenithra.data.model.toDomainModel
import com.rahul.zenithra.domain.model.User
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val sessionDao: SessionDao
) : UserRepository {

    override suspend fun getUserByEmail(email: String): User? {
        return userDao.getUserByEmail(email)?.toDomainModel()
    }

    override suspend fun signUp(userEntity: UserEntity) {
        return userDao.insertUser(userEntity)
    }
    override suspend fun saveSession(userId: Int) {
        sessionDao.saveSession(SessionEntity(userId = userId, isLoggedIn = true))
    }

    override suspend fun getSession(): SessionEntity? = sessionDao.getSession()

    override suspend fun clearSession() = sessionDao.clearSession()

}