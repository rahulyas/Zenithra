package com.rahul.zenithra.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rahul.zenithra.core.common.Converters
import com.rahul.zenithra.data.dao.MangaDao
import com.rahul.zenithra.data.dao.SessionDao
import com.rahul.zenithra.data.dao.UserDao
import com.rahul.zenithra.data.model.MangaEntity
import com.rahul.zenithra.data.model.SessionEntity
import com.rahul.zenithra.data.model.UserEntity

@Database(entities = [UserEntity::class, SessionEntity::class, MangaEntity::class], version = 4, exportSchema = false)
@TypeConverters(Converters::class)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun sessionDao(): SessionDao
    abstract fun mangaDao(): MangaDao
}