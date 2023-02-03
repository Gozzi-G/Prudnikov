package com.pr.moviekp.presentation.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pr.moviekp.domain.repositories.MovieRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber

class MoviesViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    fun create() {

    }


    init {
        viewModelScope.launch {
            movieRepository.getTopMovies()
                .onEach {
                    Timber.tag("Timber").d("MoviesViewModel: ${it}")
                }
                .catch {
                    Timber.tag("Timber").e("Error: ${it}")
                }
                .collect()

        }
    }

}