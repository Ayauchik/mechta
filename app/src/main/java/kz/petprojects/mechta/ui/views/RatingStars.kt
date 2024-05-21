import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarHalf
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kz.petprojects.mechta.ui.theme.magentaDye
import kotlin.math.ceil
import kotlin.math.floor

@Composable
fun RatingStars(rating: Float) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
       // val fullStars = rating.toInt()
        val maxRating = 5
        val starSize = 20.dp
        val fullStars = floor(rating).toInt()  // Number of full stars
        val fractionalPart = rating - fullStars
        repeat(fullStars) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = magentaDye,
                modifier = Modifier.size(starSize)
            )
        }
        if (fractionalPart > 0) {
            Icon(
                imageVector = Icons.Default.StarHalf,
                contentDescription = null,
                tint = magentaDye,
                modifier = Modifier.size(starSize)
            )
        }

        repeat(maxRating - fullStars - if (fractionalPart > 0) 1 else 0) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier.size(starSize)
            )
        }


    }
}

@Preview
@Composable
fun stars() {
    val rating = 4f
    RatingStars(rating = rating)
}
