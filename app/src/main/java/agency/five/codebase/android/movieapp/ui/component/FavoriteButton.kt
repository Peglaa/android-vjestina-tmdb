package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

fun onImageClick() {

}

@Composable
fun FavoriteButton(
    modifier: Modifier,
    contentDescription: Int = R.string.fav_button,
    isFavorite: Boolean,
    onClick: () -> Unit,
) {
    Image(
        painter = painterResource(
            id = if (isFavorite) R.drawable.ic_favorite_selected else R.drawable.ic_favorite
        ),
        contentDescription = stringResource(id = contentDescription),
        modifier = modifier.clickable { onClick() }
    )
}

@Preview(showBackground = true)
@Composable
private fun FavoriteButtonPreview() {
    val favButtonModifier = Modifier
        .padding(10.dp)
        .size(28.dp)

    FavoriteButton(
        modifier = favButtonModifier,
        onClick = { onImageClick() },
        isFavorite = false
    )
}
