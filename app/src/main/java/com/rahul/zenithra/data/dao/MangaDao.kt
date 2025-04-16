package com.rahul.zenithra.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rahul.zenithra.data.model.MangaEntity

@Dao
interface MangaDao {

    @Query("SELECT * FROM manga")
    suspend fun getAllManga(): List<MangaEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMangaList(mangaList: List<MangaEntity>)

    @Query("DELETE FROM manga")
    suspend fun clearAll()
}
