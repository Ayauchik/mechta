package kz.petprojects.mechta.ui.smartphone

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kz.petprojects.mechta.domain.model.Smartphone
import kz.petprojects.mechta.ui.navigation.Destinations
import kz.petprojects.mechta.ui.theme.backgroundColor
import kz.petprojects.mechta.ui.views.SmartphoneItem
import org.koin.androidx.compose.get

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun SmartphoneScreen(
    onItemClicked: (String, String) -> Unit,
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

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    if (isSearchVisible) {
                        TextField(
                            value = searchQuery,
                            onValueChange = { query -> viewModel.updateSearchQuery(query) },
                            placeholder = { Text(text = "Search") },
                            trailingIcon = {
                                IconButton(
                                    onClick = {
                                        if (searchQuery.isEmpty()) {
                                            isSearchVisible = false
                                        }

                                        focusManager.clearFocus()
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = "Close"
                                    )
                                }
                            },
                            modifier = Modifier.fillMaxWidth(),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    focusManager.clearFocus()
                                }
                            ),
                            singleLine = true,
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = MaterialTheme.colorScheme.background,
                                unfocusedContainerColor = MaterialTheme.colorScheme.background,
                                focusedIndicatorColor = Color.White,
                                unfocusedIndicatorColor = Color.White
                            )
                        )
                    } else {
                        Text(text = "Смартфоны")
                    }
                },
                actions = {
                    if (!isSearchVisible) {
                        IconButton(onClick = { isSearchVisible = true }) {
                            Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        DisplayingSmartphoneItems(
            paddingValues,
            isLoading,
            listState,
            smartphones,
            onItemClicked,
            isLoadingMore,
            coroutineScope,
            viewModel,
        )
    }
}

@Composable
fun DisplayingSmartphoneItems(
    paddingValues: PaddingValues,
    isLoading: Boolean,
    listState: LazyGridState,
    smartphones: List<Smartphone>,
    onItemClicked: (String, String) -> Unit,
    isLoadingMore: Boolean,
    coroutineScope: CoroutineScope,
    viewModel: SmartphoneScreenViewModel,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            Column {
                Spacer(modifier = Modifier.size(4.dp))

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    state = listState,
                    contentPadding = PaddingValues(4.dp),
                ) {
                    itemsIndexed(smartphones) { index, smartphone ->
                        SmartphoneItem(
                            smartphone = smartphone,
                        )
                        {code, firstUrl ->
                            onItemClicked(code, firstUrl)
                        }

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