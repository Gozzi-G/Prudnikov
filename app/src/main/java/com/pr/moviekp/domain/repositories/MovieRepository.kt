package com.pr.moviekp.domain.repositories

import com.pr.moviekp.data.network.model.TopMoviesDto
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getTopMovies(): Flow<TopMoviesDto>

}