package kz.petprojects.mechta.ui.smartphone
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import kz.petprojects.mechta.ui.views.SmartphoneItem
import org.koin.androidx.compose.get


@Composable
fun SmartphoneScreen(
    viewModel: SmartphoneScreenViewModel = get()
) {
    val isLoading by viewModel.isLoading
    val isLoadingMore by viewModel.isLoadingMore
    val smartphones = viewModel.smartphone.collectAsState().value
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize()) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(
                state = listState,
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                itemsIndexed(smartphones) { index, smartphone ->
                    SmartphoneItem(smartphone = smartphone)

                    if (index == smartphones.size - 1 && !isLoadingMore) {
                        LaunchedEffect(Unit) {
                            coroutineScope.launch {
                                viewModel.loadMoreData()
                            }
                        }
                    }
                }
                if (isLoadingMore) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }
    }
}
