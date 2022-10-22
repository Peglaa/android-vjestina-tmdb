package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.model.MovieDetails
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FavoriteButton(
    modifier: Modifier = Modifier,
    contentDescription: String = "Favorite button",
    movieDetails: MovieDetails
){

    Image(
        painter = painterResource(id = if(movieDetails.movie.isFavorite) R.drawable.ic_favorite_selected else R.drawable.ic_favorite),
        contentDescription = contentDescription,
        modifier = modifier
            .width(28.dp)
            .width(28.dp)
            .clickable {
                movieDetails.movie.isFavorite = !movieDetails.movie.isFavorite
            })
}

@Preview(showBackground = true)
@Composable
private fun FavoriteButtonPreview() {
    val movieDetails = MoviesMock.getMovieDetails()
    FavoriteButton(movieDetails = movieDetails)
}