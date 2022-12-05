package agency.five.codebase.android.movieapp.ui.home

import agency.five.codebase.android.movieapp.data.repository.MovieRepository
import agency.five.codebase.android.movieapp.model.MovieCategory
import agency.five.codebase.android.movieapp.ui.home.mapper.HomeScreenMapper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    private val movieRepository: MovieRepository,
    homeScreenMapper: HomeScreenMapper,
) : ViewModel() {

    private val popularCategories = listOf(
        MovieCategory.POPULAR_STREAMING,
        MovieCategory.POPULAR_ON_TV,
        MovieCategory.POPULAR_FOR_RENT,
        MovieCategory.POPULAR_IN_THEATERS
    )

    private val nowPlayingCategories = listOf(
        MovieCategory.NOW_PLAYING_MOVIES,
        MovieCategory.NOW_PLAYING_TV
    )

    private val upcomingCategories = listOf(
        MovieCategory.UPCOMING_TODAY,
        MovieCategory.UPCOMING_THIS_WEEK
    )

    private val _popularMoviesHomeViewState = MutableStateFlow(HomeMovieCategoryViewState.INITIAL_EMPTY)
    val popularMoviesHomeViewState: StateFlow<HomeMovieCategoryViewState> =
        _popularMoviesHomeViewState.asStateFlow()

    private val _nowPlayingMoviesHomeViewState = MutableStateFlow(HomeMovieCategoryViewState.INITIAL_EMPTY)
    val nowPlayingMoviesHomeViewState: StateFlow<HomeMovieCategoryViewState> =
        _nowPlayingMoviesHomeViewState.asStateFlow()

    private val _upcomingMoviesHomeViewState = MutableStateFlow(HomeMovieCategoryViewState.INITIAL_EMPTY)
    val upcomingMoviesHomeViewState: StateFlow<HomeMovieCategoryViewState> =
        _upcomingMoviesHomeViewState.asStateFlow()

    init {
        getPopularMovies(homeScreenMapper)
        getUpcomingMovies(homeScreenMapper)
        getNowPlayingMovies(homeScreenMapper)
    }

    private fun getPopularMovies(mapper: HomeScreenMapper) {
        viewModelScope.launch {
            movieRepository.popularMovies(MovieCategory.POPULAR_STREAMING).collect {
                _popularMoviesHomeViewState.value = mapper.toHomeMovieCategoryViewState(
                    movieCategories = popularCategories,
                    selectedMovieCategory = MovieCategory.POPULAR_STREAMING,
                    movies = it
                )
            }
        }
    }

    private fun getUpcomingMovies(mapper: HomeScreenMapper) {
        viewModelScope.launch {
            movieRepository.upcomingMovies(MovieCategory.UPCOMING_TODAY).collect {
                _upcomingMoviesHomeViewState.value = mapper.toHomeMovieCategoryViewState(
                    movieCategories = upcomingCategories,
                    selectedMovieCategory = MovieCategory.UPCOMING_TODAY,
                    movies = it
                )
            }
        }
    }

    private fun getNowPlayingMovies(mapper: HomeScreenMapper) {
        viewModelScope.launch {
            movieRepository.nowPlayingMovies(MovieCategory.NOW_PLAYING_MOVIES).collect {
                _nowPlayingMoviesHomeViewState.value =
                    mapper.toHomeMovieCategoryViewState(
                        movieCategories = nowPlayingCategories,
                        selectedMovieCategory = MovieCategory.NOW_PLAYING_MOVIES,
                        movies = it
                    )
            }
        }
    }

    fun toggleFavorite(id: Int) {
        viewModelScope.launch {
            movieRepository.toggleFavorite(id)
        }
    }

}
