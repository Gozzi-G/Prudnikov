package com.pr.moviekp.di

import com.pr.moviekp.presentation.movies.movies.detail.MovieDetailViewModel
import com.pr.moviekp.presentation.movies.movies.favourite.FavouriteMovieViewModel
import com.pr.moviekp.presentation.movies.movies.list.MovieListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelsModule = module {

    viewModelOf(::MovieListViewModel)
    viewModelOf(::FavouriteMovieViewModel)

    viewModel { parameters -> MovieDetailViewModel(get(), filmId = parameters.get()) }

}