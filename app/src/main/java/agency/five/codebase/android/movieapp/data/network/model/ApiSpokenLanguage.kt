package agency.five.codebase.android.movieapp.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

private const val SER_SPOKEN_LANGUAGE_ISO = "iso_639_1"
private const val SER_SPOKEN_LANGUAGE_NAME = "name"

@Serializable
data class SpokenLanguage(
    @SerialName(SER_SPOKEN_LANGUAGE_ISO)
    val iso_639_1: String,

    @SerialName(SER_SPOKEN_LANGUAGE_NAME)
    val name: String
)
