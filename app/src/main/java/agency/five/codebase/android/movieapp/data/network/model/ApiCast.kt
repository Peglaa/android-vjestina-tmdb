package agency.five.codebase.android.movieapp.data.network.model

import agency.five.codebase.android.movieapp.data.network.BASE_IMAGE_URL
import agency.five.codebase.android.movieapp.model.Actor
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

private const val SER_CAST_ADULT = "adult"
private const val SER_CAST_CAST_ID = "cast_id"
private const val SER_CAST_CHARACTER = "character"
private const val SER_CAST_CREDIT_ID = "credit_id"
private const val SER_CAST_GENDER = "gender"
private const val SER_CAST_ID = "id"
private const val SER_CAST_KNOWN_FOR_DEPARTMENT = "known_for_department"
private const val SER_CAST_NAME = "name"
private const val SER_CAST_ORDER = "order"
private const val SER_CAST_ORIGINAL_NAME = "original_name"
private const val SER_CAST_POPULARITY = "popularity"
private const val SER_CAST_PROFILE_PATH = "profile_path"

@Serializable
data class ApiCast(
    @SerialName(SER_CAST_ADULT)
    val adult: Boolean,

    @SerialName(SER_CAST_CAST_ID)
    val cast_id: Int,

    @SerialName(SER_CAST_CHARACTER)
    val character: String,

    @SerialName(SER_CAST_CREDIT_ID)
    val credit_id: String,

    @SerialName(SER_CAST_GENDER)
    val gender: Int?,

    @SerialName(SER_CAST_ID)
    val id: Int,

    @SerialName(SER_CAST_KNOWN_FOR_DEPARTMENT)
    val known_for_department: String,

    @SerialName(SER_CAST_NAME)
    val name: String,

    @SerialName(SER_CAST_ORDER)
    val order: Int,

    @SerialName(SER_CAST_ORIGINAL_NAME)
    val original_name: String,

    @SerialName(SER_CAST_POPULARITY)
    val popularity: Double,

    @SerialName(SER_CAST_PROFILE_PATH)
    val profile_path: String?
){
    fun toActor() = Actor(
        id = id,
        name = name,
        character = character,
        imageUrl = "$BASE_IMAGE_URL/$profile_path"
    )
}
