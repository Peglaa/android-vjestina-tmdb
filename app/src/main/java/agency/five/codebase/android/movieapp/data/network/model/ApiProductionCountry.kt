package agency.five.codebase.android.movieapp.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

private const val SER_PRODUCTION_COUNTRY_ISO = "iso_3166_1"
private const val SER_PRODUCTION_COUNTRY_NAME = "name"

@Serializable
data class ProductionCountry(
    @SerialName(SER_PRODUCTION_COUNTRY_ISO)
    val iso_3166_1: String,

    @SerialName(SER_PRODUCTION_COUNTRY_NAME)
    val name: String
)
