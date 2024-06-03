package kz.petprojects.mechta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import kz.petprojects.mechta.ui.navigation.Destinations
import kz.petprojects.mechta.ui.navigation.Destinations.SmartphoneDetails.firstUrl
import kz.petprojects.mechta.ui.navigation.Main
import kz.petprojects.mechta.ui.navigation.SmartphoneDetails
import kz.petprojects.mechta.ui.smartphone.SmartphoneScreen
import kz.petprojects.mechta.ui.smartphoneDetails.SmartphoneDetailsScreen
import kz.petprojects.mechta.ui.theme.MechtaTheme
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MechtaApp()
        }
    }
}


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MechtaApp() {
    MechtaTheme {
        val navController = rememberNavController()

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            NavHost(navController = navController, startDestination = Main) {
                composable<Main> {
                    SmartphoneScreen(
                        onItemClicked = { code, firstUrl ->
                            val encodedUrl =
                                URLEncoder.encode(firstUrl, StandardCharsets.UTF_8.toString())
                            navController.navigate(
                                SmartphoneDetails(
                                    code, encodedUrl
                                )
                                //Destinations.SmartphoneDetails.createRoute(code, encodedUrl)
                            )
                        },
                    )
                }

                composable<SmartphoneDetails> {
                    val args = it.toRoute<SmartphoneDetails>()
                    SmartphoneDetailsScreen(
                        smartphoneCode = args.code,
                        navController = navController,
                        firstUrl = args.firstUrl
                    )
                }
            }

//            NavHost(navController = navController, startDestination = Destinations.main) {
//
//                composable(Destinations.main) {
//                    SmartphoneScreen(
//                        onItemClicked = { code, firstUrl ->
//                            val encodedUrl = URLEncoder.encode(firstUrl, StandardCharsets.UTF_8.toString())
//                            navController.navigate(
//                                Destinations.SmartphoneDetails.createRoute(code, encodedUrl)
//                            )
//                        },
//                    )
//                }
//
//
//                composable(
//                    route = Destinations.SmartphoneDetails.route,
//                    arguments = listOf(
//                        navArgument(Destinations.SmartphoneDetails.codeArg) {
//                            type = NavType.StringType
//                        },
//                        navArgument(Destinations.SmartphoneDetails.firstUrl) {
//                            type = NavType.StringType
//                        }
//                    )
//                ) {
//                    val code = it.arguments?.getString(Destinations.SmartphoneDetails.codeArg) ?: ""
//                    val firstUrl =
//                        it.arguments?.getString(Destinations.SmartphoneDetails.firstUrl) ?: ""
//                    SmartphoneDetailsScreen(
//                        smartphoneCode = code,
//                        navController = navController,
//                        firstUrl = firstUrl
//                    )
//                }
//            }
        }
    }
}