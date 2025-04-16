package com.rahul.zenithra.domain.repository

import com.rahul.zenithra.domain.model.Manga

interface MangaRepository {
    suspend fun getManga(page: Int, isConnected: Boolean): List<Manga>
}
