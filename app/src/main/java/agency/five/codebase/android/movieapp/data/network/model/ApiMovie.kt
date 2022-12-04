package agency.five.codebase.android.movieapp.data.network.model

import agency.five.codebase.android.movieapp.data.network.BASE_IMAGE_URL
import agency.five.codebase.android.movieapp.model.Movie
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

private const val SER_API_MOVIE_ID = "id"
private const val SER_API_MOVIE_TITLE = "title"
private const val SER_API_MOVIE_OVERVIEW = "overview"
private const val SER_API_MOVIE_POSTER_PATH = "poster_path"
private const val SER_API_MOVIE_GENRE_IDS = "genre_ids"
private const val SER_API_MOVIE_VOTE_AVERAGE = "vote_average"
private const val SER_API_MOVIE_RELEASE_DATE = "release_date"

@Serializable
data class ApiMovie(
    @SerialName(SER_API_MOVIE_ID)
    val id: Int,

    @SerialName(SER_API_MOVIE_TITLE)
    val title: String,

    @SerialName(SER_API_MOVIE_OVERVIEW)
    val overview: String,

    @SerialName(SER_API_MOVIE_POSTER_PATH)
    val posterPath: String?,

    @SerialName(SER_API_MOVIE_GENRE_IDS)
    val genreIds: List<Int>,

    @SerialName(SER_API_MOVIE_VOTE_AVERAGE)
    val voteAverage: Double,

    @SerialName(SER_API_MOVIE_RELEASE_DATE)
    val releaseDate: String? = null,
) {
    fun toMovie(isFavorite: Boolean) = Movie(
        id = id,
        title = title,
        overview = overview,
        imageUrl = "$BASE_IMAGE_URL/$posterPath",
        isFavorite = isFavorite
    )
}
