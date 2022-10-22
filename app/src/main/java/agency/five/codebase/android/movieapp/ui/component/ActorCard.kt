package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.mock.MoviesMock
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

data class ActorCardViewState(
    val imageUrl: String?,
    val name: String,
    val character: String,
)

@Composable
fun ActorCard(
    actorCardViewState: ActorCardViewState,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .height(220.dp)
            .width(145.dp)
            .padding(5.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = 5.dp,
    ) {
        Column {
            AsyncImage(
                model = actorCardViewState.imageUrl,
                contentDescription = actorCardViewState.name,
                modifier = modifier
                    .height(135.dp),
                contentScale = ContentScale.Crop
            )

            Text(
                modifier = modifier
                    .padding(start = 10.dp, top = 5.dp)
                    .width(100.dp),
                text = actorCardViewState.name,
                fontSize = 14.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )

            Text(
                modifier = modifier
                    .padding(start = 10.dp, top = 5.dp),
                text = actorCardViewState.character,
                fontSize = 12.sp,
                color = Color.Gray
                )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ActorCardPreview() {
    val actor = MoviesMock.getActor()
    val actorCardViewState = ActorCardViewState(
        name = actor.name,
        imageUrl = actor.imageUrl,
        character = actor.character
    )

    ActorCard(actorCardViewState = actorCardViewState)
}