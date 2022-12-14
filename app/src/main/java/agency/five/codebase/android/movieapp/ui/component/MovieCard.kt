package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.mock.MoviesMock
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

data class MovieCardViewState(
    val id: Int,
    val imageUrl: String?,
    val title: String,
    val isFavorite: Boolean
)

@Composable
fun MovieCard(
    modifier: Modifier,
    movieCardViewState: MovieCardViewState,
    onFavoriteButtonClicked: (Int) -> Unit,
    onClick: (Int) -> Unit
) {
    Card(
        modifier = modifier.clickable { onClick(movieCardViewState.id) },
        shape = RoundedCornerShape(15.dp),
        elevation = 5.dp
    ) {
        Box {
            AsyncImage(
                model = movieCardViewState.imageUrl,
                contentDescription = movieCardViewState.title,
                contentScale = ContentScale.Crop
            )
            FavoriteButton(
                modifier = Modifier
                    .padding(10.dp)
                    .size(28.dp),
                isFavorite = movieCardViewState.isFavorite,
                onClick = { onFavoriteButtonClicked(movieCardViewState.id) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MovieCardPreview() {
    val movie = MoviesMock.getMoviesList()[0]
    val movieCardViewState = MovieCardViewState(
        id = movie.id,
        imageUrl = movie.imageUrl,
        title = movie.title,
        isFavorite = movie.isFavorite,
    )

    val movieCardModifier = Modifier
        .padding(5.dp)
        .width(120.dp)
        .height(180.dp)

    MovieCard(
        movieCardViewState = movieCardViewState,
        modifier = movieCardModifier,
        onClick = { },
        onFavoriteButtonClicked = { }
    )
}
