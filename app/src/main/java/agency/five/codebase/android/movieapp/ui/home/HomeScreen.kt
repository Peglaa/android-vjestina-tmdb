package agency.five.codebase.android.movieapp.ui.home

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.ui.component.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeRoute(
    viewModel: HomeViewModel,
    onNavigateToMovieDetails: (Int) -> Unit
) {
    val upcomingMoviesViewState: HomeMovieCategoryViewState by viewModel.upcomingMoviesHomeViewState.collectAsState()
    val nowPlayingMoviesViewState: HomeMovieCategoryViewState by viewModel.nowPlayingMoviesHomeViewState.collectAsState()
    val popularMoviesViewState: HomeMovieCategoryViewState by viewModel.popularMoviesHomeViewState.collectAsState()

    HomeScreen(
        popularCategoryViewState = popularMoviesViewState,
        nowPlayingCategoryViewState = nowPlayingMoviesViewState,
        upcomingCategoryViewState = upcomingMoviesViewState,
        onNavigateToMovieDetails = onNavigateToMovieDetails,
        onFavoriteButtonClicked = { viewModel.toggleFavorite(it) } ,
        onMovieCategoryClicked = { categoryId -> viewModel.changeCategory(categoryId) }
    )
}

@Composable
private fun HomeScreen(
    onNavigateToMovieDetails: (Int) -> Unit,
    onFavoriteButtonClicked: (Int) -> Unit,
    onMovieCategoryClicked: (Int) -> Unit,
    popularCategoryViewState: HomeMovieCategoryViewState,
    nowPlayingCategoryViewState: HomeMovieCategoryViewState,
    upcomingCategoryViewState: HomeMovieCategoryViewState
) {
    Column(
        modifier = Modifier
            .verticalScroll(
                state = rememberScrollState(),
                enabled = true
            )
    )
    {
        MovieCategoryLayout(
            modifier = Modifier,
            categoryViewState = popularCategoryViewState,
            title = R.string.whats_popular,
            onNavigateToMovieDetails = onNavigateToMovieDetails,
            onFavoriteButtonClicked = onFavoriteButtonClicked,
            onCategoryClicked = onMovieCategoryClicked

        )

        MovieCategoryLayout(
            modifier = Modifier,
            categoryViewState = nowPlayingCategoryViewState,
            title = R.string.now_playing,
            onNavigateToMovieDetails = onNavigateToMovieDetails,
            onFavoriteButtonClicked = onFavoriteButtonClicked,
            onCategoryClicked = onMovieCategoryClicked
        )

        MovieCategoryLayout(
            modifier = Modifier,
            categoryViewState = upcomingCategoryViewState,
            title = R.string.upcoming,
            onNavigateToMovieDetails = onNavigateToMovieDetails,
            onFavoriteButtonClicked = onFavoriteButtonClicked,
            onCategoryClicked = onMovieCategoryClicked
        )
    }
}

@Composable
private fun MovieCategoryLayout(
    modifier: Modifier,
    categoryViewState: HomeMovieCategoryViewState,
    title: Int,
    onNavigateToMovieDetails: (Int) -> Unit,
    onFavoriteButtonClicked: (Int) -> Unit,
    onCategoryClicked: (Int) -> Unit
) {
    Text(
        modifier = Modifier
            .padding(20.dp),
        text = stringResource(id = title),
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black
    )

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .padding(
                start = 20.dp,
                end = 20.dp,
                bottom = 20.dp
            )
    ) {
        items(
            items = categoryViewState.movieCategories,
            key = { category -> category.itemId }) { category ->
            MovieCategoryLabel(
                movieCategoryLabelViewState = category,
                modifier = Modifier.padding(5.dp),
                onClick = { onCategoryClicked(it) }
            )
        }
    }

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .padding(
                start = 20.dp,
                end = 20.dp,
                bottom = 20.dp
            ),
        content = {
            items(
                items = categoryViewState.movies,
                key = { movie -> movie.id }) { movie ->
                MovieCard(
                    modifier = Modifier
                        .width(120.dp)
                        .height(180.dp),
                    movieCardViewState = MovieCardViewState(
                        id = movie.id,
                        imageUrl = movie.imageUrl,
                        title = movie.title,
                        isFavorite = movie.isFavorite
                    ),
                    onFavoriteButtonClicked = { onFavoriteButtonClicked(movie.id) },
                    onClick = { onNavigateToMovieDetails(movie.id) }
                )
            }
        }
    )
}

/*
private fun mapToViewState(
    categoryList: List<MovieCategory>,
    categoryId: Int
): HomeMovieCategoryViewState {
    return homeScreenMapper.toHomeMovieCategoryViewState(
        movieCategories = categoryList,
        selectedMovieCategory = MovieCategory.values()[categoryId],
        movies = MoviesMock.getMoviesList()
    )
}*/
