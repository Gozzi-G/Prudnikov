package com.pr.moviekp.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class TopMoviesDto(
    @SerialName("films")
    val films: List<FilmDto?>?,
    @SerialName("pagesCount")
    val pagesCount: Int?
)

