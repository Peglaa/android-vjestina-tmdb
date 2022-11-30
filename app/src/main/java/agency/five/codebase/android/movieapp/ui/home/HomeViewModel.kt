package agency.five.codebase.android.movieapp.ui.home

import agency.five.codebase.android.movieapp.data.repository.MovieRepository
import agency.five.codebase.android.movieapp.model.MovieCategory
import agency.five.codebase.android.movieapp.ui.home.mapper.HomeScreenMapper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
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

    private val _popularMoviesHomeViewState = MutableStateFlow(HomeMovieCategoryViewState())
    val popularMoviesHomeViewState: StateFlow<HomeMovieCategoryViewState> =
        _popularMoviesHomeViewState.asStateFlow()

    private val _nowPlayingMoviesHomeViewState = MutableStateFlow(HomeMovieCategoryViewState())
    val nowPlayingMoviesHomeViewState: StateFlow<HomeMovieCategoryViewState> =
        _nowPlayingMoviesHomeViewState.asStateFlow()

    private val _upcomingMoviesHomeViewState = MutableStateFlow(HomeMovieCategoryViewState())
    val upcomingMoviesHomeViewState: StateFlow<HomeMovieCategoryViewState> =
        _upcomingMoviesHomeViewState.asStateFlow()

    init {
        viewModelScope.launch {
            _upcomingMoviesHomeViewState.value = homeScreenMapper.toHomeMovieCategoryViewState(
                movieCategories = upcomingCategories,
                selectedMovieCategory = MovieCategory.UPCOMING_TODAY,
                movies = movieRepository.upcomingMovies(MovieCategory.UPCOMING_TODAY)
                    .stateIn(this).value
            )

            _popularMoviesHomeViewState.value = homeScreenMapper.toHomeMovieCategoryViewState(
                movieCategories = popularCategories,
                selectedMovieCategory = MovieCategory.POPULAR_STREAMING,
                movies = movieRepository.upcomingMovies(MovieCategory.POPULAR_STREAMING)
                    .stateIn(this).value
            )

            _nowPlayingMoviesHomeViewState.value = homeScreenMapper.toHomeMovieCategoryViewState(
                movieCategories = nowPlayingCategories,
                selectedMovieCategory = MovieCategory.NOW_PLAYING_MOVIES,
                movies = movieRepository.upcomingMovies(MovieCategory.NOW_PLAYING_MOVIES)
                    .stateIn(this).value
            )
        }
    }

    fun toggleFavorite(id: Int) {
        viewModelScope.launch {
            movieRepository.toggleFavorite(id)
        }
    }

}
