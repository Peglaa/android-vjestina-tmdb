package agency.five.codebase.android.movieapp.ui.favorites.mapper

import agency.five.codebase.android.movieapp.model.Movie
import agency.five.codebase.android.movieapp.ui.component.MovieCardViewState
import agency.five.codebase.android.movieapp.ui.favorites.FavoritesMovieViewState
import agency.five.codebase.android.movieapp.ui.favorites.FavoritesViewState

class FavoritesMapperImpl: FavoritesMapper {
    override fun toFavoritesViewState(favoriteMovies: List<Movie>): FavoritesViewState {
        return FavoritesViewState(
            favoriteMoviesViewStates = favoriteMovies.map { mapMovie(it) }
        )
    }

    private fun mapMovie(movie: Movie): FavoritesMovieViewState{
        return FavoritesMovieViewState(
            id= movie.id,
            movieCardViewState = MovieCardViewState(
                id = movie.id,
                imageUrl = movie.imageUrl,
                title = movie.title,
                isFavorite = movie.isFavorite
            )
        )
    }

}
