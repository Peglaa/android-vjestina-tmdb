package agency.five.codebase.android.movieapp.ui.favorites

import agency.five.codebase.android.movieapp.data.repository.MovieRepository
import agency.five.codebase.android.movieapp.ui.favorites.mapper.FavoritesMapper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val movieRepository: MovieRepository,
    private val favoritesScreenMapper: FavoritesMapper,
) : ViewModel() {
    private val _favoritesViewState = MutableStateFlow(FavoritesViewState())
    val favoritesViewState: StateFlow<FavoritesViewState> = _favoritesViewState.asStateFlow()

    init {
        getFavorites()
    }

    private fun getFavorites() {
        viewModelScope.launch {
            movieRepository.favoriteMovies().collect {
                _favoritesViewState.value = favoritesScreenMapper.toFavoritesViewState(it)
            }

            println("FAVORITES: " + _favoritesViewState.value)
            println("FAV_PUBLIC: " + favoritesViewState.value)
        }
    }

    fun toggleFavorite(movieId: Int) {
        viewModelScope.launch {
            movieRepository.toggleFavorite(movieId)
        }
    }

}
