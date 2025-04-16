package com.rahul.zenithra.data.remote

import com.rahul.zenithra.data.model.MangaEntity

data class MangaDto(
    val id: String,
    val title: String?,
    val subTitle: String?,
    val status: String?,
    val thumb: String?,
    val summary: String?,
    val authors: List<String>,
    val genres: List<String>,
    val nsfw: Boolean?,
    val type: String?,
    val totalChapter: Int?,
    val createAt: Int?,
    val updateAt: Int?
)


fun MangaDto.toEntity(): MangaEntity = MangaEntity(
    id = id ?: "",
    title = title,
    subTitle = subTitle,
    status = status,
    thumb = thumb,
    summary = summary,
    authors = authors,
    genres = genres,
    nsfw = nsfw,
    type = type,
    totalChapter = totalChapter,
    createAt = createAt,
    updateAt = updateAt
)