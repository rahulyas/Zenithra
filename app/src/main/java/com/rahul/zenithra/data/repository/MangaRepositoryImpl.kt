package com.rahul.zenithra.data.repository

import com.rahul.zenithra.data.dao.MangaDao
import com.rahul.zenithra.data.model.toDomain
import com.rahul.zenithra.data.remote.MangaApiService
import com.rahul.zenithra.data.remote.toEntity
import com.rahul.zenithra.domain.model.Manga
import com.rahul.zenithra.domain.repository.MangaRepository
import javax.inject.Inject

class MangaRepositoryImpl @Inject constructor(
    private val api: MangaApiService,
    private val dao: MangaDao
) : MangaRepository {
    override suspend fun getManga(page: Int, isConnected: Boolean): List<Manga> {
        return try {
            if (isConnected) {
                val response = api.fetchMangaList(page = page)
                val entityList = response.data.map { it.toEntity() }

                // Update cache
                if (page == 1) {
                    dao.clearAll()
                }
                dao.insertMangaList(entityList)
            }

            // Get from local DB (even when online)
            dao.getAllManga().map { it.toDomain() }
        } catch (e: Exception) {
            // In case of error (like 403 or no internet), load from DB
            dao.getAllManga().map { it.toDomain() }
        }
    }
}
