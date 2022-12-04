package agency.five.codebase.android.movieapp.data.network.model

import agency.five.codebase.android.movieapp.model.Actor
import agency.five.codebase.android.movieapp.model.Crewman
import agency.five.codebase.android.movieapp.model.Movie
import agency.five.codebase.android.movieapp.model.MovieDetails
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

private const val SER_API_MOVIE_DETAILS_ADULT = "adult"
private const val SER_API_MOVIE_DETAILS_BACKDROP_PATH = "backdrop_path"
private const val SER_API_MOVIE_DETAILS_BELONGS_TO_COLLECTION = "belongs_to_collection"
private const val SER_API_MOVIE_DETAILS_BUDGET = "budget"
private const val SER_API_MOVIE_DETAILS_GENRES = "genres"
private const val SER_API_MOVIE_DETAILS_HOMEPAGE = "homepage"
private const val SER_API_MOVIE_DETAILS_ID = "id"
private const val SER_API_MOVIE_DETAILS_IMDB_ID = "imdb_id"
private const val SER_API_MOVIE_DETAILS_ORIGINAL_LANGUAGE = "original_language"
private const val SER_API_MOVIE_DETAILS_ORIGINAL_TITLE = "original_title"
private const val SER_API_MOVIE_DETAILS_OVERVIEW = "overview"
private const val SER_API_MOVIE_DETAILS_POPULARITY = "popularity"
private const val SER_API_MOVIE_DETAILS_POSTER_PATH = "poster_path"
private const val SER_API_MOVIE_DETAILS_PRODUCTION_COMPANIES = "production_companies"
private const val SER_API_MOVIE_DETAILS_PRODUCTION_COUNTRIES = "production_countries"
private const val SER_API_MOVIE_DETAILS_RELEASE_DATE = "release_date"
private const val SER_API_MOVIE_DETAILS_REVENUE = "revenue"
private const val SER_API_MOVIE_DETAILS_RUNTIME = "runtime"
private const val SER_API_MOVIE_DETAILS_SPOKEN_LANGUAGES = "spoken_languages"
private const val SER_API_MOVIE_DETAILS_STATUS = "status"
private const val SER_API_MOVIE_DETAILS_TAGLINE = "tagline"
private const val SER_API_MOVIE_DETAILS_TITLE = "title"
private const val SER_API_MOVIE_DETAILS_VIDEO = "video"
private const val SER_API_MOVIE_DETAILS_VOTE_AVERAGE = "vote_average"
private const val SER_API_MOVIE_DETAILS_VOTE_COUNT = "vote_count"


@Serializable
data class ApiMovieDetails(
    @SerialName(SER_API_MOVIE_DETAILS_ADULT)
    val adult: Boolean,

    @SerialName(SER_API_MOVIE_DETAILS_BACKDROP_PATH)
    val backdrop_path: String?,

    @Contextual
    @SerialName(SER_API_MOVIE_DETAILS_BELONGS_TO_COLLECTION)
    val belongs_to_collection: BelongsToCollection?,

    @SerialName(SER_API_MOVIE_DETAILS_BUDGET)
    val budget: Int,

    @SerialName(SER_API_MOVIE_DETAILS_GENRES)
    val genres: List<Genre>,

    @SerialName(SER_API_MOVIE_DETAILS_HOMEPAGE)
    val homepage: String?,

    @SerialName(SER_API_MOVIE_DETAILS_ID)
    val id: Int,

    @SerialName(SER_API_MOVIE_DETAILS_IMDB_ID)
    val imdb_id: String?,

    @SerialName(SER_API_MOVIE_DETAILS_ORIGINAL_LANGUAGE)
    val original_language: String,

    @SerialName(SER_API_MOVIE_DETAILS_ORIGINAL_TITLE)
    val original_title: String,

    @SerialName(SER_API_MOVIE_DETAILS_OVERVIEW)
    val overview: String?,

    @SerialName(SER_API_MOVIE_DETAILS_POPULARITY)
    val popularity: Double,

    @SerialName(SER_API_MOVIE_DETAILS_POSTER_PATH)
    val poster_path: String?,

    @SerialName(SER_API_MOVIE_DETAILS_PRODUCTION_COMPANIES)
    val production_companies: List<ProductionCompany>,

    @SerialName(SER_API_MOVIE_DETAILS_PRODUCTION_COUNTRIES)
    val production_countries: List<ProductionCountry>,

    @SerialName(SER_API_MOVIE_DETAILS_RELEASE_DATE)
    val release_date: String,

    @SerialName(SER_API_MOVIE_DETAILS_REVENUE)
    val revenue: Int,

    @SerialName(SER_API_MOVIE_DETAILS_RUNTIME)
    val runtime: Int?,

    @SerialName(SER_API_MOVIE_DETAILS_SPOKEN_LANGUAGES)
    val spoken_languages: List<SpokenLanguage>,

    @SerialName(SER_API_MOVIE_DETAILS_STATUS)
    val status: String,

    @SerialName(SER_API_MOVIE_DETAILS_TAGLINE)
    val tagline: String?,

    @SerialName(SER_API_MOVIE_DETAILS_TITLE)
    val title: String,

    @SerialName(SER_API_MOVIE_DETAILS_VIDEO)
    val video: Boolean,

    @SerialName(SER_API_MOVIE_DETAILS_VOTE_AVERAGE)
    val vote_average: Double,

    @SerialName(SER_API_MOVIE_DETAILS_VOTE_COUNT)
    val vote_count: Int
){
    fun toMovieDetails(movie: Movie, crew: List<Crewman>, cast: List<Actor>) = MovieDetails(
        movie = movie,
        voteAverage = vote_average,
        releaseDate = release_date,
        language = original_language,
        runtime = runtime,
        crew = crew,
        cast = cast
    )
}
