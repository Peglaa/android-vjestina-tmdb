package agency.five.codebase.android.movieapp.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

private const val SER_PRODUCTION_COMPANY_ID = "id"
private const val SER_PRODUCTION_COMPANY_LOGO_PATH = "logo_path"
private const val SER_PRODUCTION_COMPANY_NAME = "name"
private const val SER_PRODUCTION_COMPANY_ORIGIN_COUNTRY = "origin_country"

@Serializable
data class ProductionCompany(
    @SerialName(SER_PRODUCTION_COMPANY_ID)
    val id: Int,

    @SerialName(SER_PRODUCTION_COMPANY_LOGO_PATH)
    val logo_path: String? = null,

    @SerialName(SER_PRODUCTION_COMPANY_NAME)
    val name: String,

    @SerialName(SER_PRODUCTION_COMPANY_ORIGIN_COUNTRY)
    val origin_country: String
)
