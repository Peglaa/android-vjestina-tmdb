package agency.five.codebase.android.movieapp.ui.home

import agency.five.codebase.android.movieapp.ui.component.MovieCategoryLabelViewState

data class HomeMovieCategoryViewState(
    val movieCategories: List<MovieCategoryLabelViewState>,
    val movies: List<HomeMovieViewState>
){
    companion object {
        val INITIAL_EMPTY: HomeMovieCategoryViewState = HomeMovieCategoryViewState(
            movieCategories = emptyList(),
            movies = emptyList()
        )
    }
}

data class HomeMovieViewState(
    val id: Int,
    val title: String,
    val imageUrl: String?,
    val isFavorite: Boolean
)
