package com.rahul.zenithra.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rahul.zenithra.domain.model.Manga

@Entity(tableName = "manga")
data class MangaEntity(
    @PrimaryKey val id: String,
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


fun MangaEntity.toDomain(): Manga = Manga(
    id, title, subTitle, status, thumb, summary,
    authors, genres, nsfw, type, totalChapter, createAt, updateAt
)
