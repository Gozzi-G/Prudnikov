package com.pr.moviekp.data.network

import FilmInfoDto
import com.pr.moviekp.data.network.model.TopMoviesDto
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class KtorMovieDataSource(private val httpClient: HttpClient) {

    companion object {
        private const val QUERY_PARAM_TOP_100_TYPE = "type"
        private const val QUERY_PARAM_TOP_100 = "TOP_100_POPULAR_FILMS"

        private const val QUERY_PARAM_ID = "id"

    }

    suspend fun getTopMovies(): TopMoviesDto {

        val response = httpClient.get("api/v2.2/films/top") {
            parameter(QUERY_PARAM_TOP_100_TYPE, QUERY_PARAM_TOP_100)
            parameter("page", "1")
        }

        return response.body()
    }

    suspend fun getDescriptionMovie(id: String): FilmInfoDto {
        val response = httpClient.get() {
            url.path("api/v2.2/films/$id")
        }

        return response.body()
    }


}