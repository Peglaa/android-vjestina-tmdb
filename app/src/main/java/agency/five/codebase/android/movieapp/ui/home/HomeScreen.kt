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
import androidx.compose.runtime.*
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
fun HomeRoute(
    onNavigateToMovieDetails: (Int) -> Unit,
    onFavoriteButtonClicked: () -> Unit,
) {
    var popularCategoryViewState by remember { mutableStateOf(popularCategoryViewState) }
    var nowPlayingCategoryViewState by remember { mutableStateOf(nowPlayingCategoryViewState) }
    var upcomingCategoryViewState by remember { mutableStateOf(upcomingCategoryViewState) }

    HomeScreen(
        popularCategoryViewState = popularCategoryViewState,
        nowPlayingCategoryViewState = nowPlayingCategoryViewState,
        upcomingCategoryViewState = upcomingCategoryViewState,
        onNavigateToMovieDetails = onNavigateToMovieDetails,
        onFavoriteButtonClicked = { onFavoriteButtonClicked() },
        onMovieCategoryClicked = { categoryId, categoryList ->
            /*
            THIS WORKS EXCEPT IT REFUSES TO RECOMPOSE ON CLICK AND THE CHANGE IS ONLY VISIBLE
            AFTER NAVIGATING OUT OF AND THEN BACK INTO THE HOME SCREEN
            I TRIED WITH COPY() BUT IT'S STILL NOT TRIGGERING AND I HAVE NO IDEA WHY
            ALSO I PRESUME THERE IS A CLEANER WAY OF IMPLEMENTING THIS ON CLICK FUNCTION?
             */

            val newList: MutableList<MovieCategoryLabelViewState> = mutableListOf()
            val tmp = when (categoryId) {
                in (0..3) -> categoryId
                in (4..5) -> categoryId - 4
                in (6..7) -> categoryId - 6
                else -> throw IllegalStateException()
            }

            categoryList.forEachIndexed { index, movieCategoryLabelViewState ->
                newList.add(
                    movieCategoryLabelViewState.copy(
                        isSelected = index == tmp
                    )
                )
            }
            when (categoryId) {
                in (0..3) -> {
                    popularCategoryViewState =
                        nowPlayingCategoryViewState.copy(
                            movieCategories = newList
                        )
                }
                in (4..5) -> {
                    nowPlayingCategoryViewState =
                        nowPlayingCategoryViewState.copy(
                            movieCategories = newList
                        )
                }
                in (6..7) -> {
                    upcomingCategoryViewState =
                        upcomingCategoryViewState.copy(
                            movieCategories = newList
                        )
                }
                else -> throw IllegalStateException()
            }
        }
    )
}

@Composable
fun HomeScreen(
    onNavigateToMovieDetails: (Int) -> Unit,
    onFavoriteButtonClicked: () -> Unit,
    onMovieCategoryClicked: (Int, List<MovieCategoryLabelViewState>) -> Unit,
    popularCategoryViewState: HomeMovieCategoryViewState,
    nowPlayingCategoryViewState: HomeMovieCategoryViewState,
    upcomingCategoryViewState: HomeMovieCategoryViewState
) {
    //println("currentRecomposeScopeHOME $currentRecomposeScope")
    Column(
        modifier = Modifier
            .verticalScroll(
                state = rememberScrollState(),
                enabled = true
            )
    )
    {

        /*
        I HAVE NOTICED THAT THESE COMPONENTS(MOVIECATEGORYLAYOUT()) RECOMPOSE TWICE WHEN NAVIGATING TO THIS SCREEN.
        SOMETHING MIGHT BE WRONG WITH THEM BUT IM NOT SURE WHAT AND IT MIGHT BE CONNECTED TO THEM
        NOT RECOMPOSING WHEN CLICKING ON A LABEL AS DESCRIBED ABOVE AND IF I HAD TO GUESS THE FAULT
        LIES IN MY MOVIECATEGORYLABEL COMPONENT
        */

        MovieCategoryLayout(
            modifier = Modifier,
            categoryViewState = popularCategoryViewState,
            title = R.string.whats_popular,
            onNavigateToMovieDetails = onNavigateToMovieDetails,
            onFavoriteButtonClicked = { onFavoriteButtonClicked() },
            onCategoryClicked = onMovieCategoryClicked

        )

        MovieCategoryLayout(
            modifier = Modifier,
            categoryViewState = nowPlayingCategoryViewState,
            title = R.string.now_playing,
            onNavigateToMovieDetails = onNavigateToMovieDetails,
            onFavoriteButtonClicked = { onFavoriteButtonClicked() },
            onCategoryClicked = onMovieCategoryClicked
        )

        MovieCategoryLayout(
            modifier = Modifier,
            categoryViewState = upcomingCategoryViewState,
            title = R.string.upcoming,
            onNavigateToMovieDetails = onNavigateToMovieDetails,
            onFavoriteButtonClicked = { onFavoriteButtonClicked() },
            onCategoryClicked = onMovieCategoryClicked
        )
    }
}

@Composable
fun MovieCategoryLayout(
    modifier: Modifier,
    categoryViewState: HomeMovieCategoryViewState,
    title: Int,
    onNavigateToMovieDetails: (Int) -> Unit,
    onFavoriteButtonClicked: () -> Unit,
    onCategoryClicked: (Int, List<MovieCategoryLabelViewState>) -> Unit
) {
    println("currentRecomposeScopeCATEGORY $currentRecomposeScope")
    println("categories ${categoryViewState.movieCategories}")

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
                onClick = { onCategoryClicked(it, categoryViewState.movieCategories) }
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
                    onFavoriteButtonClicked = { onFavoriteButtonClicked() },
                    onClick = { onNavigateToMovieDetails(movie.id) }
                )
            }
        }
    )
}


/*@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        onFavoriteButtonClicked = { },
        onNavigateToMovieDetails = { },
        onMovieCategoryClicked = { _, _ ->

        }
    )

}*/


