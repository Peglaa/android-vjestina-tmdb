package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.R
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

sealed class MovieCategoryLabelTextViewState {
    class InputText(val text: String) : MovieCategoryLabelTextViewState()
    class ResourceText(@StringRes val textRes: Int) : MovieCategoryLabelTextViewState()
}

data class MovieCategoryLabelViewState(
    val itemId: Int,
    var isSelected: Boolean,
    val categoryText: MovieCategoryLabelTextViewState
)

@Composable
fun MovieCategoryLabel(
    movieCategoryLabelViewState: MovieCategoryLabelViewState,
    modifier: Modifier,
    onClick: (Int) -> Unit
) {
    if (movieCategoryLabelViewState.isSelected) {
        SelectedText(
            movieCategoryLabelViewState = movieCategoryLabelViewState,
            modifier = modifier
                .width(intrinsicSize = IntrinsicSize.Max)
        )
    } else {
        UnselectedText(
            movieCategoryLabelViewState = movieCategoryLabelViewState,
            modifier = modifier,
            onClick = onClick
        )
    }
}

@Composable
fun SelectedText(
    movieCategoryLabelViewState: MovieCategoryLabelViewState,
    modifier: Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = selectTextSource(movieCategoryLabelViewState = movieCategoryLabelViewState),
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(5.dp))
        Divider(color = Color.Black, thickness = 4.dp, modifier = Modifier.fillMaxWidth())
    }
}

@Composable
fun UnselectedText(
    movieCategoryLabelViewState: MovieCategoryLabelViewState,
    modifier: Modifier,
    onClick: (Int) -> Unit
) {
    Text(
        text = selectTextSource(movieCategoryLabelViewState = movieCategoryLabelViewState),
        color = Color.Gray,
        fontSize = 16.sp,
        modifier = modifier.clickable { onClick(movieCategoryLabelViewState.itemId) }
    )
}

@Composable
fun selectTextSource(movieCategoryLabelViewState: MovieCategoryLabelViewState): String {
    return when (val categoryText = movieCategoryLabelViewState.categoryText) {
        is MovieCategoryLabelTextViewState.InputText -> categoryText.text
        is MovieCategoryLabelTextViewState.ResourceText -> stringResource(id = categoryText.textRes)
    }
}

@Preview(showBackground = true)
@Composable
fun MovieCategoryLabelPreview() {
    //val inputText = MovieCategoryLabelTextViewState.InputText("Movies")
    val stringRes = MovieCategoryLabelTextViewState.ResourceText(R.string.app_name)
    //val categoryViewState1 = MovieCategoryLabelViewState(2, false, inputText)
    val categoryViewState2 = MovieCategoryLabelViewState(1, true, stringRes)
    MovieCategoryLabel(
        movieCategoryLabelViewState = categoryViewState2,
        modifier = Modifier.padding(5.dp),
        onClick = {}
    )
}
