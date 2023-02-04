package com.pr.moviekp.domain.models

import kotlin.random.Random

data class FilmItem(
    val filmId: Int?,
    val title: String?,
    val genres: List<String>?,
    val year: String?,
    val posterUrl: String?,
    val countries: List<String>?,
    val isFavourite: Boolean = Random.nextBoolean(),
)