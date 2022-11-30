package agency.five.codebase.android.movieapp.ui.favorites

import agency.five.codebase.android.movieapp.ui.component.MovieCard
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun FavoritesRoute(
    viewModel: FavoritesViewModel,
    onNavigateToMovieDetails: (Int) -> Unit,
) {
    val favoritesViewState: FavoritesViewState by viewModel.favoritesViewState.collectAsState()
    println("FAV_VIEW_STATE: $favoritesViewState")

    FavoritesScreen(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
        favoritesViewState = favoritesViewState,
        onCardClick = onNavigateToMovieDetails,
        onFavoriteButtonClick = { viewModel.toggleFavorite(it) }
    )
}

//Extension function for scrollable header cause it's a cleaner approach(to me)?
private fun LazyGridScope.header(
    content: @Composable LazyGridItemScope.() -> Unit
) {
    item(
        span = { GridItemSpan(this.maxLineSpan) },
        content = content
    )
}

@Composable
private fun FavoritesScreen(
    modifier: Modifier,
    favoritesViewState: FavoritesViewState,
    onCardClick: (Int) -> Unit,
    onFavoriteButtonClick: (Int) -> Unit
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
                key = { movie -> movie.id }
            ) { card ->
                MovieCard(
                    modifier = Modifier
                        .width(120.dp)
                        .height(180.dp),
                    movieCardViewState = card.movieCardViewState,
                    onClick = { onCardClick(card.id) },
                    onFavoriteButtonClicked = { onFavoriteButtonClick(card.movieCardViewState.id) }
                )
            }
        },
        modifier = modifier
    )
}

/*
@Preview
@Composable
private fun FavoritesScreenPreview() {
    val favoritesScreenModifier = Modifier
        .fillMaxSize()
        .padding(15.dp)

    MovieAppTheme {
        FavoritesScreen(
            modifier = favoritesScreenModifier,
            favoritesViewState = favoritesViewState,
            onCardClick = {  },
            onFavoriteButtonClick = {  }
        )
    }
}*/
