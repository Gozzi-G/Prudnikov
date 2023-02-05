package com.pr.moviekp.data.repositories

import com.pr.moviekp.data.db.AppDatabase
import com.pr.moviekp.data.mapper.FilmMapper
import com.pr.moviekp.data.network.KtorMovieDataSource
import com.pr.moviekp.domain.models.FilmItem
import com.pr.moviekp.domain.repositories.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class MovieRepositoryImpl(
    private val remoteDataSource: KtorMovieDataSource,
    private val mapper: FilmMapper,
    private val database: AppDatabase,
) : MovieRepository {

    private val favouriteFilmDao = database.favouriteFilmsDao()


    override suspend fun getFilmList(): Flow<List<FilmItem>> {

        return flow {
            emit(favouriteFilmDao.getFilmList())
        }.flatMapMerge {
            val data = if (it.isEmpty()) {
                val response = remoteDataSource.getTopMovies()
                val dbModelList = response.films?.map { mapper.mapDtoToDbModel(it) } ?: emptyList()
                favouriteFilmDao.insertFilmList(dbModelList)
                favouriteFilmDao.getFilmList()
                mapper.mapListDbModelToListEntity(it)
            } else {
                mapper.mapListDbModelToListEntity(it)
            }
            flow {
                emit(data)
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getFilmDetailInfo(id: String): Flow<FilmItem> {
        return flow {
            emit(remoteDataSource.getDescriptionMovie(id))
        }.map {
            mapper.mapDtoToModel(it)
        }
    }

    override suspend fun addToFavourite(filmItem: FilmItem) {
        favouriteFilmDao.addFilmItem(mapper.mapEntityToDbModel(filmItem))
    }
}