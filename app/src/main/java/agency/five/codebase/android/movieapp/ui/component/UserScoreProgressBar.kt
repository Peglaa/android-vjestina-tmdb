package agency.five.codebase.android.movieapp.ui.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import agency.five.codebase.android.movieapp.ui.theme.GreenProgressBar
import agency.five.codebase.android.movieapp.ui.theme.GreenProgressBarBackground

private const val SCORE_FORMAT = "%.1f"

@Composable
fun UserScoreProgressBar(
    modifier: Modifier,
    score: Float,
    radius: Dp = 30.dp,
    colorProgressBar: Color = GreenProgressBar,
    colorProgressBarBackground: Color = GreenProgressBarBackground,
    strokeWidth: Dp = 4.dp,
    animDuration: Int = 1000
) {
    val animationPlayed = remember { mutableStateOf(false) }
    val currentPercentage = animateFloatAsState(
        targetValue = (if (animationPlayed.value) score else 0f),
        animationSpec = tween(
            durationMillis = animDuration
        )
    )

    LaunchedEffect(key1 = true) {
        animationPlayed.value = true
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.size(radius * 2f)
    ) {
        Canvas(
            modifier = Modifier
                .padding(5.dp)
                .size(radius * 2f)
        ) {
            drawArc(
                color = colorProgressBarBackground,
                startAngle = -90f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
            drawArc(
                color = colorProgressBar,
                startAngle = -90f,
                sweepAngle = 360 * currentPercentage.value,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }

        Text(
            text = SCORE_FORMAT.format(score * 10f),
            fontSize = 15.sp,
            color = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CircularProgressBarPreview() {
    UserScoreProgressBar(
        score = 0.08f,
        modifier = Modifier
    )
}
