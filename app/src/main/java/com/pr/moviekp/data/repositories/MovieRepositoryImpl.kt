package com.pr.moviekp.data.repositories

import com.pr.moviekp.data.mapper.FilmMapper
import com.pr.moviekp.data.network.KtorMovieDataSource
import com.pr.moviekp.domain.models.FilmItem
import com.pr.moviekp.domain.repositories.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class MovieRepositoryImpl(
    private val remoteDataSource: KtorMovieDataSource,
    private val mapper: FilmMapper
) : MovieRepository {


    override suspend fun getTopMovies(): Flow<List<FilmItem>> {
        return flow {
            emit(remoteDataSource.getTopMovies())
        }.map { mapper.mapListDTOModelToListModel(it.films) }
    }

}