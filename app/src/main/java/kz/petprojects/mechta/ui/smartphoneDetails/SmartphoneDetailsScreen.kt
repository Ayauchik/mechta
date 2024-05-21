package kz.petprojects.mechta.ui.smartphoneDetails

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import org.koin.androidx.compose.get

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun SmartphoneDetailsScreen(
    smartphoneCode: String,
    viewModel: SmartphoneDetailsViewModel = get()
) {
    val isLoading by viewModel.isLoading
    val smartphoneDetails by viewModel.smartphoneDetails.collectAsState()

    LaunchedEffect(smartphoneCode) {
        viewModel.fetchSmartphoneDetails(smartphoneCode)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            smartphoneDetails?.let { details ->
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = details.name, style = MaterialTheme.typography.bodySmall)
                    Spacer(modifier = Modifier.height(8.dp))
                    LazyRow {
                        items(details.photos) { photo ->
                            GlideImage(
                                model = photo,
                                contentDescription = null,
                                modifier = Modifier.size(200.dp),
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Rating: ${details.rating}",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        text = "Reviews: ${details.reviewsCount}",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Properties:", style = MaterialTheme.typography.bodySmall)
                    details.mainProperties.forEach { property ->
                        Text(
                            text = "${property.promName}: ${property.propValue}",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            } ?: run {
                Text(
                    text = "Smartphone details not found.",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}
