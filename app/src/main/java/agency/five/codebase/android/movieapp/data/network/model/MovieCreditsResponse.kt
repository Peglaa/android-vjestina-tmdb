package agency.five.codebase.android.movieapp.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

private const val SER_MOVIE_CREDITS_RESPONSE_CAST = "cast"
private const val SER_MOVIE_CREDITS_RESPONSE_CREW = "crew"
private const val SER_MOVIE_CREDITS_RESPONSE_ID = "id"

@Serializable
data class MovieCreditsResponse(
    @SerialName(SER_MOVIE_CREDITS_RESPONSE_CAST)
    val cast: List<ApiCast>,

    @SerialName(SER_MOVIE_CREDITS_RESPONSE_CREW)
    val crew: List<ApiCrew>,

    @SerialName(SER_MOVIE_CREDITS_RESPONSE_ID)
    val id: Int
)
