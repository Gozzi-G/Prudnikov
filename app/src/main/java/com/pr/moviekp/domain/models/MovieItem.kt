package com.pr.moviekp.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FilmItem(
    val filmId: Int,
    val title: String,
    val genres: String,
    val year: String,
    val posterUrl: String,
    val countries: String = "",
    val isFavourite: Boolean,
    val description: String = ""
) : Parcelable