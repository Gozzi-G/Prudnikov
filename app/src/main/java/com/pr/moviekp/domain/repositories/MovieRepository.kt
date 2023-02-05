package com.pr.moviekp.domain.repositories

import com.pr.moviekp.domain.models.FilmItem
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getFilmList(): Flow<List<FilmItem>>
    suspend fun getFilmDetailInfo(id: String): Flow<FilmItem>
    suspend fun addToFavourite(filmItem: FilmItem)
    suspend fun removeFromFavourite(filmItem: FilmItem)
    suspend fun getFilmsFromDb(): Flow<List<FilmItem>>

}