package com.rahul.zenithra.di

import com.rahul.zenithra.data.repository.MangaRepositoryImpl
import com.rahul.zenithra.data.repository.UserRepositoryImpl
import com.rahul.zenithra.domain.repository.MangaRepository
import com.rahul.zenithra.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {
    @Binds
    fun bindUserRepository(impl: UserRepositoryImpl): UserRepository

//    @Binds
//    fun bindMangaRepository(impl: MangaRepositoryImpl): MangaRepository


}