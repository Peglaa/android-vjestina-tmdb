package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.model.Movie
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

@Composable
fun MovieCard(
    modifier: Modifier = Modifier,
    movie: Movie
){
    Card(
        modifier = modifier
            .padding(5.dp)
            .width(120.dp)
            .height(180.dp)
            .clickable {  },
        shape = RoundedCornerShape(15.dp),
        elevation = 5.dp
    ) {
        Box{
            AsyncImage(
                model = movie.imageUrl,
                contentDescription = movie.title,
                contentScale = ContentScale.Crop
            )
            FavoriteButton()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieCardPreview(){
    val movie = MoviesMock.getMoviesList()[0]
    MovieCard(movie = movie)
}