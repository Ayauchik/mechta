package kz.petprojects.mechta.ui.smartphone

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import kz.petprojects.mechta.ui.views.SmartphoneItem
import org.koin.androidx.compose.get

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SmartphoneScreen(
    viewModel: SmartphoneScreenViewModel = get()
) {
    val isLoading by viewModel.isLoading
    val isLoadingMore by viewModel.isLoadingMore
    val smartphones = viewModel.filteredSmartphones.collectAsState().value
    val searchQuery by viewModel.searchQuery
    val listState = rememberLazyGridState()
    val coroutineScope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current

    var isSearchVisible by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp)
                        .animateContentSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    if (!isSearchVisible) {
                        Text(text = "Смартфоны", style = MaterialTheme.typography.titleMedium)
                        IconButton(onClick = { isSearchVisible = true }) {
                            Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                        }
                    } else {
                        TextField(
                            value = searchQuery,
                            onValueChange = { query -> viewModel.updateSearchQuery(query) },
                            placeholder = { Text(text = "Search") },
                            trailingIcon = {
                                IconButton(
                                    onClick = {
                                        focusManager.clearFocus()
                                        if(searchQuery == ""){
                                            isSearchVisible = false
                                        }
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Search,
                                        contentDescription = "Search"
                                    )
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    focusManager.clearFocus()
                                }
                            ),
                        )

                    }

                }


                Spacer(modifier = Modifier.size(4.dp))

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    state = listState,
                    contentPadding = PaddingValues(4.dp),
                    // verticalArrangement = Arrangement.spacedBy(2.dp),
                    // horizontalArrangement = Arrangement.spacedBy(2.dp)
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
}
