package kz.petprojects.mechta.ui.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kz.petprojects.mechta.domain.model.getSmartphones.Smartphone

@Composable
fun SmartphoneItem(smartphone: Smartphone) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            PhotoCarousel(photos = smartphone.photos)
            Text(text = smartphone.name, style = MaterialTheme.typography.bodyLarge)
            Text(text = "Code: ${smartphone.code}", style = MaterialTheme.typography.bodySmall)
            Text(text = "Price: ${smartphone.price}", style = MaterialTheme.typography.bodySmall)
            Text(text = "Bonus: ${smartphone.bonus}", style = MaterialTheme.typography.bodySmall)
            // You can add more details or images if needed
        }
    }
}
