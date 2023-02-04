package com.pr.moviekp.data.mapper

import com.pr.moviekp.data.network.model.FilmDto
import com.pr.moviekp.domain.models.FilmItem

class FilmMapper {

    private fun mapDtoToModel(dto: FilmDto?): FilmItem {
        return FilmItem(
            filmId = dto?.filmId ?: -1,
            title = dto?.nameRu ?: "",
            genres = dto?.genres?.map {
                it?.genre ?: ""
            },
            year = dto?.year ?: "",
            posterUrl = dto?.posterUrl,
            countries = dto?.countries?.map {
                it?.country ?: ""
            })
    }

    fun mapListDTOModelToListModel(list: List<FilmDto?>?) = list?.map {
        mapDtoToModel(it)
    } ?: emptyList()
}
