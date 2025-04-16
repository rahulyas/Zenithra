package com.rahul.zenithra.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rahul.zenithra.data.model.SessionEntity

@Dao
interface SessionDao {
    @Query("SELECT * FROM session WHERE id = 0")
    suspend fun getSession(): SessionEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSession(session: SessionEntity)

    @Query("DELETE FROM session")
    suspend fun clearSession()
}
