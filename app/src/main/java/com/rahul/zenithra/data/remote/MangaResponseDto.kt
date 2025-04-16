package com.rahul.zenithra.data.remote

import com.google.gson.annotations.SerializedName

data class MangaResponseDto(
    @SerializedName("data")
    val data: List<MangaDto>
)
