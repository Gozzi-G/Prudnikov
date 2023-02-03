package com.pr.moviekp.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FilmDto(
    @SerialName("countries")
    val countries: List<CountryDto?>?,
    @SerialName("filmId")
    val filmId: Int?,
    @SerialName("filmLength")
    val filmLength: String?,
    @SerialName("genres")
    val genres: List<GenreDto?>?,
    @SerialName("nameEn")
    val nameEn: String?,
    @SerialName("nameRu")
    val nameRu: String?,
    @SerialName("posterUrl")
    val posterUrl: String?,
    @SerialName("posterUrlPreview")
    val posterUrlPreview: String?,
    @SerialName("rating")
    val rating: String?,
    @SerialName("ratingVoteCount")
    val ratingVoteCount: Int?,
    @SerialName("year")
    val year: String?
)