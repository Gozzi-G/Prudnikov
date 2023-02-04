package com.pr.moviekp.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FilmDto(
    @SerialName("countries")
    val countries: List<CountryDto?>?,
    @SerialName("filmId")
    val filmId: Int?,
    @SerialName("genres")
    val genres: List<GenreDto?>?,
    @SerialName("nameRu")
    val nameRu: String?,
    @SerialName("posterUrl")
    val posterUrl: String?,
    @SerialName("posterUrlPreview")
    val posterUrlPreview: String?,
    @SerialName("year")
    val year: String?,
)



