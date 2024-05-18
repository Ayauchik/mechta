package kz.petprojects.mechta.ui.smartphone

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.toList
import kz.petprojects.mechta.ui.views.SmartphoneItem
import org.koin.androidx.compose.get


@Composable
fun SmartphoneScreen(
    viewModel: SmartphoneScreenViewModel = get()
){
    val isLoading by viewModel.isLoading
    val smartphones = viewModel.smartphone.collectAsState().value
    Log.e("screen", smartphones.toString())

    Box(modifier = Modifier.fillMaxSize()) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(smartphones) { smartphone ->
                    SmartphoneItem(smartphone = smartphone)
                }
            }
        }
    }
}