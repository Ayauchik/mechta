package kz.petprojects.mechta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kz.petprojects.mechta.ui.navigation.Destinations
import kz.petprojects.mechta.ui.smartphone.SmartphoneScreen
import kz.petprojects.mechta.ui.smartphoneDetails.SmartphoneDetailsScreen
import kz.petprojects.mechta.ui.theme.MechtaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MechtaApp()
//            MechtaTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    //  SmartphoneScreen()
//                    SmartphoneDetailsScreen(smartphoneCode = "telefon-sotovyy-samsung-sm-a-135-galaxy-a13-64gb-flbvs-blue")
//                }
//            }
        }
    }
}

@Composable
fun MechtaApp() {
    MechtaTheme {
        val navController = rememberNavController()

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            NavHost(navController = navController, startDestination = Destinations.main) {
                composable(Destinations.main) {
                    SmartphoneScreen(navController)
                }


                composable(Destinations.SmartphoneDetails.route) {
                    val code =it.arguments?.getString(Destinations.SmartphoneDetails.codeArg)

                    SmartphoneDetailsScreen(smartphoneCode = code ?: " ")
                }
            }
        }
    }
}