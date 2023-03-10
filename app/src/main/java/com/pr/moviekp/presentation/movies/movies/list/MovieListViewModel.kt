package com.pr.moviekp.presentation.movies.movies.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pr.moviekp.base.BaseViewModel
import com.pr.moviekp.domain.models.FilmItem
import com.pr.moviekp.domain.repositories.MovieRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber
import java.net.UnknownHostException

class MovieListViewModel(private val movieRepository: MovieRepository) : BaseViewModel() {


    private var _filmList: MutableLiveData<List<FilmItem>> = MutableLiveData<List<FilmItem>>()
    val filmList: LiveData<List<FilmItem>> = _filmList

    fun onViewCreated() {
        loadFilms()
    }

    fun loadFilms() {
        loading()
        viewModelScope.launch {
            movieRepository.getFilmList()
                .onEach { items ->
                    _filmList.value = items
                    content()
                }
                .catch {
                    if (it is UnknownHostException) {
                        Timber.tag("loadFilms").d("$it")
                        loadFromLocal()
                    }

                    error()
                }
                .collect()
        }
    }

    fun loadFromLocal() {
        viewModelScope.launch {
            movieRepository.getFilmsFromDb()
                .map {
                    it.filter { it.isFavourite }
                }
                .catch {
                    error()
                }
                .collect { items ->
                    _filmList.value = items
                    content()
                }
        }
    }

    fun addToFavourite(filmItem: FilmItem) {

        viewModelScope.launch {
            val newItem = filmItem.copy(isFavourite = !filmItem.isFavourite)
            movieRepository.addToFavourite(newItem)

            val oldFilms =
                _filmList.value?.toMutableList() ?: throw java.lang.IllegalStateException()
            val newFilms = oldFilms.apply {
                replaceAll {
                    if (it.filmId == newItem.filmId) {
                        newItem
                    } else {
                        it
                    }
                }
            }

            _filmList.value = newFilms
        }

    }


}