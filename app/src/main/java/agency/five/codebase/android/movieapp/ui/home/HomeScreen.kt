package agency.five.codebase.android.movieapp.ui.home

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.model.MovieCategory
import agency.five.codebase.android.movieapp.ui.component.*
import agency.five.codebase.android.movieapp.ui.home.mapper.HomeScreenMapper
import agency.five.codebase.android.movieapp.ui.home.mapper.HomeScreenMapperImpl
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val popularCategories = listOf(
    MovieCategory.POPULAR_STREAMING,
    MovieCategory.POPULAR_ON_TV,
    MovieCategory.POPULAR_FOR_RENT,
    MovieCategory.POPULAR_IN_THEATERS
)

val nowPlayingCategories = listOf(
    MovieCategory.NOW_PLAYING_MOVIES,
    MovieCategory.NOW_PLAYING_TV
)

val upcomingCategories = listOf(
    MovieCategory.UPCOMING_TODAY,
    MovieCategory.UPCOMING_THIS_WEEK
)

val homeScreenMapper: HomeScreenMapper = HomeScreenMapperImpl()

val popularCategoryViewState = homeScreenMapper.toHomeMovieCategoryViewState(
    movieCategories = popularCategories,
    selectedMovieCategory = MovieCategory.POPULAR_STREAMING,
    movies = MoviesMock.getMoviesList()
)

val nowPlayingCategoryViewState = homeScreenMapper.toHomeMovieCategoryViewState(
    movieCategories = nowPlayingCategories,
    selectedMovieCategory = MovieCategory.NOW_PLAYING_MOVIES,
    movies = MoviesMock.getMoviesList()
)

val upcomingCategoryViewState = homeScreenMapper.toHomeMovieCategoryViewState(
    movieCategories = upcomingCategories,
    selectedMovieCategory = MovieCategory.UPCOMING_TODAY,
    movies = MoviesMock.getMoviesList()
)

@Composable
fun HomeScreen(
    modifier: Modifier,
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
            title = R.string.whats_popular
        )

        MovieCategoryLayout(
            modifier = Modifier,
            categoryViewState = nowPlayingCategoryViewState,
            title = R.string.now_playing
        )

        MovieCategoryLayout(
            modifier = Modifier,
            categoryViewState = upcomingCategoryViewState,
            title = R.string.upcoming
        )
    }

}

@Composable
fun MovieCategoryLayout(
    modifier: Modifier,
    categoryViewState: HomeMovieCategoryViewState,
    title: Int
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
                onClick = { }
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
                        imageUrl = movie.imageUrl,
                        title = movie.title,
                        isFavorite = movie.isFavorite
                    ),
                    onFavoriteButtonClicked = { /*TODO*/ },
                    onClick = { /*TODO*/ })
            }
        })
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        modifier = Modifier,
        popularCategoryViewState = popularCategoryViewState,
        nowPlayingCategoryViewState = nowPlayingCategoryViewState,
        upcomingCategoryViewState = upcomingCategoryViewState
    )
}
