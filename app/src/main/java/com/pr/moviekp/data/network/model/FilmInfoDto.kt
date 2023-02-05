
import com.pr.moviekp.data.network.model.CountryDto
import com.pr.moviekp.data.network.model.GenreDto
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName
@Serializable
data class FilmInfoDto(
    @SerialName("kinopoiskId")
    val kinopoiskId: Int?,
    @SerialName("countries")
    val countries: List<CountryDto?>?,
    @SerialName("genres")
    val genres: List<GenreDto?>?,
    @SerialName("nameRu")
    val nameRu: String?,
    @SerialName("posterUrl")
    val posterUrl: String?,
    @SerialName("year")
    val year: String?,
    @SerialName("description")
    val description: String?
)
