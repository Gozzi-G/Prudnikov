package com.pr.moviekp.data.mapper

import FilmInfoDto
import com.pr.moviekp.data.network.model.FilmDto
import com.pr.moviekp.data.repositories.FilmItemDbModel
import com.pr.moviekp.domain.models.FilmItem
import timber.log.Timber

class FilmMapper {

    private fun mapDtoToModel(dto: FilmDto?): FilmItem {
        return FilmItem(
            filmId = dto?.filmId ?: -1,
            title = dto?.nameRu ?: "",
            genres = dto?.genres?.map { it?.genre ?: "" }?.firstOrNull() ?: "",
            year = dto?.year ?: "",
            posterUrl = dto?.posterUrl ?: "",
            countries = dto?.countries?.joinToString(",") { it?.country ?: "" } ?: "",
            isFavourite = false
        )
    }

    fun mapDtoToModel(dto: FilmInfoDto?): FilmItem {
        return FilmItem(
            filmId = dto?.kinopoiskId ?: -1,
            title = dto?.nameRu ?: "",
            genres = dto?.genres?.map { it?.genre ?: "" }?.firstOrNull() ?: "",
            year = dto?.year ?: "",
            posterUrl = dto?.posterUrl ?: "",
            countries = dto?.countries?.joinToString(",") { it?.country ?: "" } ?: "",
            description = dto?.description ?: "",
            isFavourite = false
        )
    }

    fun mapEntityToDbModel(filmItem: FilmItem): FilmItemDbModel {
        return FilmItemDbModel(
            id = filmItem.filmId,
            title = filmItem.title,
            genres = filmItem.genres,
            year = filmItem.year,
            posterUrl = filmItem.posterUrl,
            countries = filmItem.countries,
            isFavourite = filmItem.isFavourite
        )
    }

    fun mapDtoToDbModel(dto: FilmDto?): FilmItemDbModel {
        return FilmItemDbModel(
            id = dto?.filmId ?: -1,
            title = dto?.nameRu ?: "",
            genres = dto?.genres?.map { it?.genre }?.firstOrNull() ?: "",
            year = dto?.year ?: "",
            posterUrl = dto?.posterUrl ?: "",
            countries = dto?.countries?.joinToString(",") { it?.country ?: "" } ?: "",
            isFavourite = false
        )
    }

    private fun mapDbModelToEntity(filmDbModel: FilmItemDbModel): FilmItem {
        return FilmItem(
            filmId = filmDbModel.id,
            title = filmDbModel.title,
            genres = filmDbModel.genres,
            year = filmDbModel.year,
            posterUrl = filmDbModel.posterUrl,
            countries = filmDbModel.countries,
            isFavourite = filmDbModel.isFavourite
        )
    }

    fun mapListDbModelToListEntity(list: List<FilmItemDbModel>) = list.map {
        mapDbModelToEntity(it)
    }


    fun mapListDTOModelToListModel(list: List<FilmDto?>?) = list?.map {
        mapDtoToModel(it)
    } ?: emptyList()
}
