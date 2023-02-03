package com.pr.moviekp.di

import com.pr.moviekp.presentation.movies.MoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelsModule = module {

    viewModelOf(::MoviesViewModel)

}