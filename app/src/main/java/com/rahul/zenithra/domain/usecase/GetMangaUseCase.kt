package com.rahul.zenithra.domain.usecase

import com.rahul.zenithra.domain.repository.MangaRepository
import javax.inject.Inject

class GetMangaUseCase @Inject constructor(
    private val repository: MangaRepository
) {
    suspend operator fun invoke(page: Int, isConnected: Boolean) =
        repository.getManga(page, isConnected)
}
