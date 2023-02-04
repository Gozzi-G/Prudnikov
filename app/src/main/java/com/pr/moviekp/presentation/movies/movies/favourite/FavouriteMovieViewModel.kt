package com.pr.moviekp.presentation.movies.movies.favourite

import androidx.lifecycle.LiveData
import com.pr.moviekp.base.BaseViewModel
import com.pr.moviekp.base.LiveEvent
import com.pr.moviekp.domain.models.FilmItem
import com.pr.moviekp.domain.repositories.MovieRepository

class FavouriteMovieViewModel(
    private val movieRepository: MovieRepository
) : BaseViewModel() {

    private var _filmList = LiveEvent<List<FilmItem>>()
    val filmList: LiveData<List<FilmItem>> = _filmList

    init {
        loadFilms()
    }

    fun loadFilms() {

    }
}