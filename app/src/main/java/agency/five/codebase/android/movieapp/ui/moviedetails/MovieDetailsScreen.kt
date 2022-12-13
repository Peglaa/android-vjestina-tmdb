package agency.five.codebase.android.movieapp.ui.moviedetails

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.ui.component.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.*
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

@Composable
fun MovieDetailsRoute(
    viewModel: MovieDetailsViewModel
) {
    val movieDetailsViewState: MovieDetailsViewState by viewModel.movieDetailsViewState.collectAsState()

    MovieDetailsScreen(
        movieDetailsViewState = movieDetailsViewState,
        onFavoriteClicked = {
            viewModel.toggleFavorite(movieDetailsViewState.id)
        }
    )
}

@Composable
private fun MovieDetailsScreen(
    movieDetailsViewState: MovieDetailsViewState,
    onFavoriteClicked: () -> Unit
) {
    Column(
        modifier = Modifier.verticalScroll(
            state = rememberScrollState(),
            enabled = true
        )
    ) {

        ImageHeader(
            modifier = Modifier.height(350.dp),
            movieDetailsViewState = movieDetailsViewState,
            onFavoriteClicked = onFavoriteClicked
        )

        Overview(
            modifier = Modifier
                .padding(20.dp),
            movieDetailsViewState = movieDetailsViewState
        )

        CrewGrid(
            modifier = Modifier
                .padding(
                    start = 20.dp,
                    end = 20.dp,
                    bottom = 20.dp
                )
                .heightIn(min = 100.dp, max = 500.dp),
            movieDetailsViewState = movieDetailsViewState
        )

        TopBilledCast(
            modifier = Modifier
                .padding(
                    start = 20.dp,
                    end = 20.dp,
                    bottom = 20.dp
                ),
            movieDetailsViewState = movieDetailsViewState
        )

    }

}

@Composable
private fun ImageHeader(
    modifier: Modifier,
    movieDetailsViewState: MovieDetailsViewState,
    onFavoriteClicked: () -> Unit
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
                isFavorite = movieDetailsViewState.isFavorite,
                onClick = onFavoriteClicked
            )
        }
    }
}

@Composable
private fun Overview(
    modifier: Modifier,
    movieDetailsViewState: MovieDetailsViewState
) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier,
            text = stringResource(id = R.string.overview),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Text(
            modifier = Modifier,
            text = movieDetailsViewState.overview,
            fontSize = 15.sp,
            color = Color.Black
        )
    }
}

@Composable
private fun CrewGrid(
    modifier: Modifier,
    movieDetailsViewState: MovieDetailsViewState
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        userScrollEnabled = false,
        content = {
            items(
                items = movieDetailsViewState.crew,
                key = { crew -> crew.id }
            ) { crewItem ->
                CrewItem(
                    crewItemViewState = CrewItemViewState(
                        id = crewItem.id,
                        crewItem.name,
                        crewItem.job
                    ),
                    modifier = Modifier
                )
            }
        }
    )
}

@Composable
private fun TopBilledCast(
    modifier: Modifier,
    movieDetailsViewState: MovieDetailsViewState
) {
    Text(
        modifier = modifier,
        text = stringResource(id = R.string.top_billed_cast),
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
            ),
        content = {
            items(
                items = movieDetailsViewState.cast,
                key = { cast -> cast.id }) { castItem ->
                ActorCard(
                    actorCardViewState = ActorCardViewState(
                        id = castItem.id,
                        imageUrl = castItem.imageUrl,
                        name = castItem.name,
                        character = castItem.character
                    ),
                    modifier = Modifier
                        .height(220.dp)
                        .width(145.dp)
                        .padding(5.dp)
                )
            }
        })
}

@Preview
@Composable
private fun MovieDetailsScreenPreview() {
    MovieDetailsScreen(
        movieDetailsViewState = MovieDetailsViewState.INITIAL_EMPTY,
        onFavoriteClicked = {}
    )
}
