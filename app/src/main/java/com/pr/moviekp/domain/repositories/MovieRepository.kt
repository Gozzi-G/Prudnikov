package com.pr.moviekp.domain.repositories

import com.pr.moviekp.domain.models.FilmItem
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getTopMovies(): Flow<List<FilmItem>>

}