package com.pr.moviekp.presentation.movies.movies.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pr.moviekp.base.BaseViewModel
import com.pr.moviekp.domain.models.FilmItem
import com.pr.moviekp.domain.repositories.MovieRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber

class MovieListViewModel(private val movieRepository: MovieRepository) : BaseViewModel() {


    private var _filmList: MutableLiveData<List<FilmItem>> = MutableLiveData<List<FilmItem>>()
    val filmList: LiveData<List<FilmItem>> = _filmList


    init {
        loadFilms()
    }

    fun loadFilms() {
        viewModelScope.launch {
            loading()
            movieRepository.getTopMovies()
                .onEach { items ->
                    Timber.tag("Timber").d("MoviesViewModel: $items")
                    _filmList.value = items
                    content()
                }
                .catch {
                    Timber.tag("Timber").e("Error: ${it}")
                    error()
                }
                .collect()
        }
    }

}