package agency.five.codebase.android.movieapp.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

private const val SER_BELONGS_TO_COLLECTION_ID = "id"
private const val SER_BELONGS_TO_COLLECTION_NAME = "name"
private const val SER_BELONGS_TO_COLLECTION_BACKDROP_PATH = "backdrop_path"
private const val SER_BELONGS_TO_COLLECTION_POSTER_PATH = "poster_path"

@Serializable
data class BelongsToCollection(
    @SerialName(SER_BELONGS_TO_COLLECTION_ID)
    val id: Int,

    @SerialName(SER_BELONGS_TO_COLLECTION_NAME)
    val name: String,

    @SerialName(SER_BELONGS_TO_COLLECTION_BACKDROP_PATH)
    val backdrop_path: String?,

    @SerialName(SER_BELONGS_TO_COLLECTION_POSTER_PATH)
    val poster_path: String?,
)
