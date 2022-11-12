package agency.five.codebase.android.movieapp.ui.moviedetails

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.ui.component.FavoriteButton
import agency.five.codebase.android.movieapp.ui.component.UserScoreProgressBar
import agency.five.codebase.android.movieapp.ui.moviedetails.mapper.MovieDetailsMapper
import agency.five.codebase.android.movieapp.ui.moviedetails.mapper.MovieDetailsMapperImpl
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

private val movieDetailsMapper: MovieDetailsMapper = MovieDetailsMapperImpl()

private val movieDetailsViewState: MovieDetailsViewState =
    movieDetailsMapper.toMovieDetailsViewState(
        MoviesMock.getMovieDetails()
    )

@Composable
fun MovieDetailsScreen(
    modifier: Modifier,
    movieDetailsViewState: MovieDetailsViewState
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier.scrollable(
            state = scrollState,
            orientation = Orientation.Vertical
        )
    ) {

        ImageHeader(
            modifier = Modifier.height(350.dp),
            movieDetailsViewState = movieDetailsViewState
        )

        Overview(
            modifier = Modifier,
            movieDetailsViewState = movieDetailsViewState
        )

    }

}

@Composable
fun ImageHeader(
    modifier: Modifier,
    movieDetailsViewState: MovieDetailsViewState
) {
    Box(
        modifier = modifier,
    ) {
        AsyncImage(
            model = movieDetailsViewState.imageUrl,
            contentDescription = movieDetailsViewState.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Spacer(modifier = Modifier.weight(0.4F))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.3F),
                verticalAlignment = Alignment.CenterVertically
            ) {
                UserScoreProgressBar(
                    modifier = Modifier.padding(10.dp),
                    score = movieDetailsViewState.voteAverage
                )
                Text(
                    modifier = Modifier,
                    text = stringResource(id = R.string.user_score),
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

            Text(
                modifier = Modifier
                    .weight(0.15F)
                    .padding(10.dp),
                text = movieDetailsViewState.title,
                fontSize = 20.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

            FavoriteButton(
                modifier = Modifier
                    .padding(10.dp)
                    .size(50.dp)
                    .weight(0.15F),
                isFavorite = movieDetailsViewState.isFavorite
            ) {

            }
        }
    }
}

@Composable
fun Overview(
    modifier: Modifier,
    movieDetailsViewState: MovieDetailsViewState
) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier.padding(
                start = 20.dp,
                bottom = 5.dp,
                top = 20.dp
            ),
            text = stringResource(id = R.string.overview),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Text(
            modifier = Modifier.padding(
                start = 20.dp,
                end = 20.dp
            ),
            text = movieDetailsViewState.overview,
            fontSize = 15.sp,
            color = Color.Black
        )
    }
}

@Preview
@Composable
fun MovieDetailsScreenPreview() {
    MovieDetailsScreen(
        modifier = Modifier,
        movieDetailsViewState = movieDetailsViewState
    )
}

