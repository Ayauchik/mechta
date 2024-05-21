package kz.petprojects.mechta.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kz.petprojects.mechta.domain.model.Properties

@Composable
fun MainPropertiesCard(
    properties: List<Properties>,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text("Основные", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.size(12.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RectangleShape

            ) {
            Column {
                properties.forEachIndexed { index, property ->
                    PropertyRow(
                        propName = property.promName,
                        propValue = property.propValue,
                        backgroundColor = if (index % 2 == 0) Color(0xFFdee2e6) else Color(0xFFe9ecef)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.size(16.dp))
    }
}

@Composable
fun PropertyRow(
    propName: String,
    propValue: String,
    modifier: Modifier = Modifier,
    nameColumnWidth: Dp = 200.dp, // Adjust this value as needed
    backgroundColor: Color
) {
    Row(
        modifier = modifier
            .background(backgroundColor)
            .padding(vertical = 4.dp)
            .padding(start = 8.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "$propName:",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.width(nameColumnWidth)
        )
        Text(
            text = propValue,
            style = MaterialTheme.typography.bodySmall
        )
    }
}
