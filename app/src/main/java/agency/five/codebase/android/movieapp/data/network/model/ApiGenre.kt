package agency.five.codebase.android.movieapp.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

private const val SER_GENRE_ID = "id"
private const val SER_GENRE_NAME = "name"

@Serializable
data class Genre(
    @SerialName(SER_GENRE_ID)
    val id: Int,

    @SerialName(SER_GENRE_NAME)
    val name: String
)
