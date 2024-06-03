package kz.petprojects.mechta.ui.views

import RatingStars
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import kz.petprojects.mechta.domain.model.SmartphoneDetails


@Composable
fun SmartphoneDetailsItem(
    details: SmartphoneDetails) {

    LazyColumn(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        item {
            PhotoCarousel(
                photos = details.photos,
                dotSize = 8.dp,
                modifier = Modifier
                    .fillMaxWidth(),
            )
        }
        item {
            Text(
                text = details.name,
                style = MaterialTheme.typography.titleLarge
            )
        }
        item {
            Row {
                RatingStars(rating = details.rating.toString().toFloatOrNull() ?: 0f)
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    text = "${details.reviewsCount} отзывов",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }

        item {
            MainPropertiesCard(properties = details.mainProperties)
        }
    }
}