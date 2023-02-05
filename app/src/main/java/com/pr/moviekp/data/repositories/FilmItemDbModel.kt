package com.pr.moviekp.data.repositories

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "film_items")
class FilmItemDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val genres: String,
    val year: String,
    val posterUrl: String,
    val countries: String,
    val isFavourite: Boolean,
)