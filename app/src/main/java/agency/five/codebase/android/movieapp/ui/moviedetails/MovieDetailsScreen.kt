package agency.five.codebase.android.movieapp.ui.moviedetails

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.ui.component.FavoriteButton
import agency.five.codebase.android.movieapp.ui.component.UserScoreProgressBar
import agency.five.codebase.android.movieapp.ui.moviedetails.mapper.MovieDetailsMapper
import agency.five.codebase.android.movieapp.ui.moviedetails.mapper.MovieDetailsMapperImpl
import androidx.compose.foundation.layout.*
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
fun ImageHeader(
    modifier: Modifier,
    movieDetailsViewState: MovieDetailsViewState
) {
    Box(
        modifier = modifier.fillMaxHeight(0.45F),
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
                    modifier = Modifier.padding(10.dp),
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

@Preview
@Composable
fun MovieDetailsScreenPreview() {
    ImageHeader(
        modifier = Modifier.fillMaxWidth(),
        movieDetailsViewState = movieDetailsViewState
    )
}

