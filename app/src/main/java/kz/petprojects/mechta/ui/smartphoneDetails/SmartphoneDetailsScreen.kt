package kz.petprojects.mechta.ui.smartphoneDetails

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import kz.petprojects.mechta.ui.theme.backgroundColor
import kz.petprojects.mechta.ui.views.FavoriteButton
import kz.petprojects.mechta.ui.views.SmartphoneDetailsItem
import org.koin.androidx.compose.get
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun SmartphoneDetailsScreen(
    smartphoneCode: String,
    firstUrl: String,
    navController: NavHostController,
    viewModel: SmartphoneDetailsViewModel = get()
) {
    val isLoading by viewModel.isLoading
    val smartphoneDetails by viewModel.smartphoneDetails.collectAsState()
    var isFavorite by remember { mutableStateOf(smartphoneDetails.inFavourites) }

    LaunchedEffect(smartphoneCode) {
        viewModel.fetchSmartphoneDetails(smartphoneCode)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    actionIconContentColor = Color.Black
                ),
                actions = {
                    FavoriteButton(
                        isFavorite = isFavorite,
                        onClick = {
                            isFavorite = !isFavorite
                            Log.e("FavoriteButton", "isFavorite: $isFavorite")
                        }
                    )
                }
            )
        },
        modifier = Modifier.padding(end = 8.dp)
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (isLoading) {
                val decodedUrl = URLDecoder.decode(firstUrl, StandardCharsets.UTF_8.toString())
                GlideImage(
                    model = decodedUrl,
                    contentDescription = "first photo for animation"
                )
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(top = 180.dp)
                        .align(Alignment.Center)
                )
            } else {
                SmartphoneDetailsItem(details = smartphoneDetails)
            }
        }
    }
}


