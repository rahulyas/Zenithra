package com.rahul.zenithra.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.*
import com.rahul.zenithra.data.model.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<UserEntity>

    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    suspend fun getUserByEmail(email: String): UserEntity?

    @Delete
    suspend fun deleteUser(user: UserEntity)
}
