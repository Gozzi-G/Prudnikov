package com.pr.moviekp.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlin.random.Random

@Parcelize
data class FilmItem(
    val filmId: Int?,
    val title: String?,
    val genres: List<String>?,
    val year: String?,
    val posterUrl: String?,
    val countries: List<String>?,
    val isFavourite: Boolean = false,
    val description: String = ""
) : Parcelable