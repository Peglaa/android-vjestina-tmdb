package agency.five.codebase.android.movieapp.ui.favorites

import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.ui.component.MovieCard
import agency.five.codebase.android.movieapp.ui.favorites.mapper.FavoritesMapper
import agency.five.codebase.android.movieapp.ui.favorites.mapper.FavoritesMapperImpl
import agency.five.codebase.android.movieapp.ui.theme.MovieAppTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

private val favoritesMapper: FavoritesMapper = FavoritesMapperImpl()

// multiple view states if required
val favoritesViewState = favoritesMapper.toFavoritesViewState(MoviesMock.getMoviesList())
/*
@Composable
fun favoritesRoute(
// actions
) {
    val z by remember { mutableStateOf(favoritesViewState) }
// ...
    FavoritesScreen(
        z,
// other states and actions
    )
}
 */

fun LazyGridScope.header(
    content: @Composable LazyGridItemScope.() -> Unit
) {
    item(span = { GridItemSpan(this.maxLineSpan) }, content = content)
}

@Composable
fun FavoritesScreen(
    modifier: Modifier,
    favoritesViewState: FavoritesViewState
) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        content = {
            header {
                Text(
                    text = "Favorites",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 15.dp)
                )
            }
            items(
                items = favoritesViewState.favoriteMoviesViewStates,
                key = { movie -> movie.id }) { card ->
                MovieCard(
                    modifier = Modifier
                        .width(120.dp)
                        .height(180.dp),
                    movieCardViewState = card.movieCardViewState,
                    onClick = {},
                    onFavoriteButtonClicked = {}
                )
            }
        },
        modifier = modifier
    )
}

@Preview
@Composable
fun FavoritesScreenPreview() {
    val favoritesScreenModifier = Modifier
        .fillMaxSize()
        .padding(15.dp)

    MovieAppTheme {
        FavoritesScreen(
            modifier = favoritesScreenModifier,
            favoritesViewState = favoritesViewState
        )
    }
}
