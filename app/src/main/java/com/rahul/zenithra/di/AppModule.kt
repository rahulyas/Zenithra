package com.rahul.zenithra.di

import android.content.Context
import androidx.room.Room
import com.rahul.zenithra.core.common.Constant.DATABASE_NAME
import com.rahul.zenithra.data.dao.MangaDao
import com.rahul.zenithra.data.dao.SessionDao
import com.rahul.zenithra.data.dao.UserDao
import com.rahul.zenithra.data.local.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun providesAppDatabase(@ApplicationContext appContext: Context): UserDatabase {
        return Room.databaseBuilder(
            appContext, UserDatabase::class.java, DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun providesUserDao(userDatabase: UserDatabase): UserDao {
        return userDatabase.userDao()
    }

    @Provides
    fun providesSessionDao(userDatabase: UserDatabase): SessionDao {
        return userDatabase.sessionDao()
    }

    @Provides
    fun provideMangaDao(userDatabase: UserDatabase): MangaDao = userDatabase.mangaDao()

}