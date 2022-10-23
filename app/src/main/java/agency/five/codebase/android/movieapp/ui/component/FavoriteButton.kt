package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

//This image is only clickable and does not receive any data atm

@Composable
fun FavoriteButton(
    modifier: Modifier = Modifier,
    contentDescription: String = "Favorite button"
){
    val fav = remember { mutableStateOf(false) }
    Image(
        painter = painterResource(id = if(fav.value) R.drawable.ic_favorite_selected else R.drawable.ic_favorite),
        contentDescription = contentDescription,
        modifier = modifier
            .padding(10.dp)
            .width(28.dp)
            .width(28.dp)
            .clickable {
                fav.value = !fav.value
            })
}

@Preview(showBackground = true)
@Composable
private fun FavoriteButtonPreview() {
    FavoriteButton()
}