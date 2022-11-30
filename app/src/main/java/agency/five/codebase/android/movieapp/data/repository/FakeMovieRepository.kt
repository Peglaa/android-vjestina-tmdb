package agency.five.codebase.android.movieapp.data.repository

import agency.five.codebase.android.movieapp.mock.FavoritesDBMock
import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.model.Movie
import agency.five.codebase.android.movieapp.model.MovieCategory
import agency.five.codebase.android.movieapp.model.MovieDetails
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*

class FakeMovieRepository(
    private val ioDispatcher: CoroutineDispatcher,
) : MovieRepository {
    private val fakeMovies = MoviesMock.getMoviesList().toMutableList()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val movies: Flow<List<Movie>> = FavoritesDBMock.favoriteIds
        .mapLatest { favoriteIds ->
            fakeMovies.map {
                movie ->
                if(favoriteIds.contains(movie.id))
                    movie.copy(isFavorite = true)
                else
                    movie.copy(isFavorite = false)
            }
        }
        .flowOn(ioDispatcher)

    override fun popularMovies(movieCategory: MovieCategory) = movies
    override fun nowPlayingMovies(movieCategory: MovieCategory) = movies
    override fun upcomingMovies(movieCategory: MovieCategory) = movies
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun movieDetails(movieId: Int): Flow<MovieDetails> =
        FavoritesDBMock.favoriteIds
            .mapLatest {
                val movieDetails = MoviesMock.getMovieDetails(movieId)
                movieDetails.copy(
                    movie = movieDetails.movie.copy(
                        isFavorite = it.contains(movieDetails.movie.id)
                ))
            }
            .flowOn(ioDispatcher)

    override fun favoriteMovies(): Flow<List<Movie>> = movies.map {
        it.filter { fakeMovie -> fakeMovie.isFavorite }
    }.transform { emit(it) }

    override suspend fun addMovieToFavorites(movieId: Int) {
        FavoritesDBMock.insert(movieId)
    }

    override suspend fun removeMovieFromFavorites(movieId: Int) {
        FavoritesDBMock.delete(movieId)
    }

    override suspend fun toggleFavorite(movieId: Int) =
        if (FavoritesDBMock.favoriteIds.value.contains(movieId)) {
            removeMovieFromFavorites(movieId)
        } else {
            addMovieToFavorites(movieId)
        }
}
