package com.pr.moviekp.domain.repositories

import Ram
import com.pr.moviekp.domain.models.FilmItem
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getTopMovies(): Flow<List<FilmItem>>
    suspend fun getDescription(id: String): Flow<Ram>

}