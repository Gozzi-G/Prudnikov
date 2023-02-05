package com.pr.moviekp.presentation.movies.movies.favourite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pr.moviekp.base.BaseViewModel
import com.pr.moviekp.domain.models.FilmItem
import com.pr.moviekp.domain.repositories.MovieRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class FavouriteMovieViewModel(
    private val movieRepository: MovieRepository
) : BaseViewModel() {

    private var _filmList = MutableLiveData<List<FilmItem>>()
    val filmList: LiveData<List<FilmItem>> = _filmList


    fun onViewCreated() {
        loadFilms()
    }

    fun loadFilms() {
        viewModelScope.launch {
            loading()
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
    fun deleteFromFavourite(filmItem: FilmItem) {

        viewModelScope.launch {
            val newItem = filmItem.copy(isFavourite = !filmItem.isFavourite)
            movieRepository.removeFromFavourite(newItem)

            val oldFilms = _filmList.value?.toMutableList() ?: throw java.lang.IllegalStateException()
            val newFilms = oldFilms.apply {
                replaceAll {
                    if (it.filmId == newItem.filmId) {
                        newItem
                    } else {
                        it
                    }
                }
            }.filter {
                it.isFavourite
            }


            _filmList.value = newFilms
        }

    }
}