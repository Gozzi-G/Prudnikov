package com.pr.moviekp.di

import androidx.room.Room
import com.pr.moviekp.data.db.AppDatabase
import com.pr.moviekp.data.mapper.FilmMapper
import com.pr.moviekp.data.network.KtorMovieDataSource
import com.pr.moviekp.data.repositories.MovieRepositoryImpl
import com.pr.moviekp.domain.repositories.MovieRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataModule = module {
    singleOf(::MovieRepositoryImpl) { bind<MovieRepository>() }
    singleOf(::KtorMovieDataSource)
    factoryOf(::FilmMapper)

    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, "film_item.db")
            .build()
    }
}