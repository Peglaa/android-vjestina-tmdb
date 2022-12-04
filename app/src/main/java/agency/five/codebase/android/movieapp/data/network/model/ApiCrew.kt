package agency.five.codebase.android.movieapp.data.network.model

import agency.five.codebase.android.movieapp.model.Crewman
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

private const val SER_CREW_ADULT = "adult"
private const val SER_CREW_CREDIT_ID = "credit_id"
private const val SER_CREW_DEPARTMENT = "department"
private const val SER_CREW_GENDER = "gender"
private const val SER_CREW_ID = "id"
private const val SER_CREW_JOB = "job"
private const val SER_CREW_KNOWN_FOR_DEPARTMENT = "known_for_department"
private const val SER_CREW_NAME = "name"
private const val SER_CREW_ORIGINAL_NAME = "original_name"
private const val SER_CREW_POPULARITY = "popularity"
private const val SER_CREW_PROFILE_PATH = "profile_path"

@Serializable
data class ApiCrew(
    @SerialName(SER_CREW_ADULT)
    val adult: Boolean,

    @SerialName(SER_CREW_CREDIT_ID)
    val credit_id: String,

    @SerialName(SER_CREW_DEPARTMENT)
    val department: String,

    @SerialName(SER_CREW_GENDER)
    val gender: Int?,

    @SerialName(SER_CREW_ID)
    val id: Int,

    @SerialName(SER_CREW_JOB)
    val job: String,

    @SerialName(SER_CREW_KNOWN_FOR_DEPARTMENT)
    val known_for_department: String,

    @SerialName(SER_CREW_NAME)
    val name: String,

    @SerialName(SER_CREW_ORIGINAL_NAME)
    val original_name: String,

    @SerialName(SER_CREW_POPULARITY)
    val popularity: Double,

    @SerialName(SER_CREW_PROFILE_PATH)
    val profile_path: String?
){
    fun toCrewman() = Crewman(
        id = id,
        name = name,
        job = job
    )
}
