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

class MovieDetailViewModel(
    private val repository: MovieRepository,
    private val filmId: String,
) : BaseViewModel() {

    private var _filmList: MutableLiveData<FilmItem> = MutableLiveData<FilmItem>()
    val filmList: LiveData<FilmItem> = _filmList

    init {
        loadFilmById()
    }

    fun loadFilmById() {
        loading()
        viewModelScope.launch {
            repository.getFilmDetailInfo(filmId)
                .onEach { item ->
                    _filmList.value = item
                    content()
                }
                .catch {
                    error()
                }
                .collect()
        }
    }
}