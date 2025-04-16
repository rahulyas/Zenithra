package com.rahul.zenithra.di

import com.rahul.zenithra.data.dao.MangaDao
import com.rahul.zenithra.data.remote.MangaApiService
import com.rahul.zenithra.data.repository.MangaRepositoryImpl
import com.rahul.zenithra.domain.repository.MangaRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object MangaModule {

    @Provides
    @Singleton
    fun provideApi(): MangaApiService = Retrofit.Builder()
        .baseUrl("https://mangaverse-api.p.rapidapi.com/")
        .client(provideOkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MangaApiService::class.java)

    private fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .addHeader("x-rapidapi-key", "9ac3c7125dmsh888d15351ca4b17p1ea535jsn2814a0fcb3de")
                .addHeader("x-rapidapi-host", "mangaverse-api.p.rapidapi.com")
                .build()
            chain.proceed(request)
        }
        .build()

    @Provides
    fun provideMangaRepository(
        api: MangaApiService,
        dao: MangaDao
    ): MangaRepository = MangaRepositoryImpl(api, dao)
}
