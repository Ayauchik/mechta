package kz.petprojects.mechta.ui.views

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import kz.petprojects.mechta.domain.model.Smartphone
import kz.petprojects.mechta.ui.theme.magentaDye

@Composable
fun SmartphoneItem(smartphone: Smartphone) {
    var isFavorite by remember { mutableStateOf(smartphone.isFavourite) }

    Card(
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
            Box {
                PhotoCarousel(photos = smartphone.photos)
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                        //.padding(8.dp),
                    contentAlignment = Alignment.TopEnd
                ) {
                    FavoriteButton(
                        isFavorite = isFavorite,
                        onClick = {
                            isFavorite = !isFavorite
                            Log.e("FavoriteButton", "isFavorite: $isFavorite")
                        }
                    )
                }
            }

            Text(
                text = smartphone.name,
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(modifier = Modifier.size(4.dp))
            val formattedPrice = smartphone.price.toString().addSpacesBetweenGroupsOfThreeFromEnd()
            Text(text = formattedPrice, style = MaterialTheme.typography.displayLarge)
        }
    }
}

@Composable
fun FavoriteButton(
    isFavorite: Boolean,
    onClick: () -> Unit
) {
    IconButton(onClick = onClick, modifier = Modifier.size(24.dp)) {
        Icon(
            imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
            contentDescription = null,
            tint = if (isFavorite) magentaDye else Color.Gray
        )
    }
}

fun String.addSpacesBetweenGroupsOfThreeFromEnd(): String {
    val reversed = this.reversed()
    val chunks = reversed.chunked(3)
    return chunks.joinToString(" ").reversed()
}
