package com.pr.moviekp.di

import com.pr.moviekp.data.network.KtorMovieDataSource
import com.pr.moviekp.data.repositories.MovieRepositoryImpl
import com.pr.moviekp.domain.repositories.MovieRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataModule = module {
    singleOf(::MovieRepositoryImpl) { bind<MovieRepository>() }
    singleOf(::KtorMovieDataSource)
}