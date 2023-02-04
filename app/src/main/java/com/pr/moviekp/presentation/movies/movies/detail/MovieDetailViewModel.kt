package com.pr.moviekp.presentation.movies.movies.detail

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

class MovieDetailViewModel(
    private val repository: MovieRepository,
    private val filmId: String,
) : BaseViewModel() {

    private var _filmList: MutableLiveData<String> = MutableLiveData<String>()
    val filmList: LiveData<String> = _filmList

    init {
        loadFilmById(filmId)
    }

    private fun loadFilmById(id: String) {
        viewModelScope.launch {
            repository.getDescription(filmId)
                .onEach { items ->
                    Timber.tag("Timber").d("MovieDetailViewModel: $items")
                    _filmList.value = items.description
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