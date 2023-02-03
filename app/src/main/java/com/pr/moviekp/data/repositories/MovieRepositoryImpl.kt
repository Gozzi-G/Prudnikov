package com.pr.moviekp.data.repositories

import com.pr.moviekp.data.network.KtorMovieDataSource
import com.pr.moviekp.data.network.model.TopMoviesDto
import com.pr.moviekp.domain.repositories.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepositoryImpl(private val remoteDataSource: KtorMovieDataSource) : MovieRepository {


    override suspend fun getTopMovies(): Flow<TopMoviesDto> {
        return flow {
            emit(remoteDataSource.getTopMovies())
        }
    }

}