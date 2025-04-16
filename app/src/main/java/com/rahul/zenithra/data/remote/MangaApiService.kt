package com.rahul.zenithra.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface MangaApiService {
    @GET("manga/fetch")
    suspend fun fetchMangaList(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 20
    ): MangaResponseDto
}
