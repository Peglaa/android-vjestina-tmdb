package agency.five.codebase.android.movieapp.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

private const val SER_MOVIE_RESPONSE_RESULTS = "results"

@Serializable
data class MovieResponse(
    @SerialName(SER_MOVIE_RESPONSE_RESULTS)
    val movies: List<ApiMovie>,
)
