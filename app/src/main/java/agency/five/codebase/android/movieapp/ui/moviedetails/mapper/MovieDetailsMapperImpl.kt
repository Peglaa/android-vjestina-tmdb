package agency.five.codebase.android.movieapp.ui.moviedetails.mapper

import agency.five.codebase.android.movieapp.model.MovieDetails
import agency.five.codebase.android.movieapp.ui.moviedetails.ActorViewState
import agency.five.codebase.android.movieapp.ui.moviedetails.CrewmanViewState
import agency.five.codebase.android.movieapp.ui.moviedetails.MovieDetailsViewState

class MovieDetailsMapperImpl : MovieDetailsMapper {
    override fun toMovieDetailsViewState(movieDetails: MovieDetails): MovieDetailsViewState {
        val crewList = mutableListOf<CrewmanViewState>()
        val castList = mutableListOf<ActorViewState>()

        for (crewman in movieDetails.crew)
            crewList.add(
                CrewmanViewState(
                    id = crewman.id,
                    name = crewman.name,
                    job = crewman.job
                )
            )

        for (actor in movieDetails.cast)
            castList.add(
                ActorViewState(
                    id = actor.id,
                    name = actor.name,
                    character = actor.character,
                    imageUrl = actor.imageUrl
                )
            )

        return MovieDetailsViewState(
            id = movieDetails.movie.id,
            imageUrl = movieDetails.movie.imageUrl,
            voteAverage = movieDetails.voteAverage,
            title = movieDetails.movie.title,
            overview = movieDetails.movie.overview,
            isFavorite = movieDetails.movie.isFavorite,
            crew = crewList,
            cast = castList
        )
    }
}