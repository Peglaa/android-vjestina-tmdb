package agency.five.codebase.android.movieapp.ui.moviedetails

import agency.five.codebase.android.movieapp.data.repository.MovieRepository
import agency.five.codebase.android.movieapp.ui.moviedetails.mapper.MovieDetailsMapper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val movieRepository: MovieRepository,
    private val movieDetailsScreenMapper: MovieDetailsMapper,
) : ViewModel() {
    private val _movieDetailsViewState = MutableStateFlow(MovieDetailsViewState())
    val movieDetailsViewState: StateFlow<MovieDetailsViewState> = _movieDetailsViewState.asStateFlow()

    init {
        viewModelScope.launch {
            _movieDetailsViewState.value = movieDetailsScreenMapper.toMovieDetailsViewState(
                movieRepository.movieDetails(_movieDetailsViewState.value.id).stateIn(this).value
            )
        }
    }

    fun toggleFavorite(movieId: Int){
        viewModelScope.launch {
            movieRepository.toggleFavorite(movieId)
        }
    }

}
