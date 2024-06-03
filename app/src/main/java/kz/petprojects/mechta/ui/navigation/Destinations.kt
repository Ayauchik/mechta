package kz.petprojects.mechta.ui.navigation

import kotlinx.serialization.Serializable

object Destinations {
    const val main = "main"

    object SmartphoneDetails {
        const val route = "pokemonDetails/{code}/{firstUrl}"
        const val codeArg = "code"
        const val firstUrl = "firstUrl"

        fun createRoute (code: String, firstUrl: String): String {
            return "pokemonDetails/$code/$firstUrl"
        }
    }

}


@Serializable
object Main

@Serializable
data class SmartphoneDetails(
    val code: String,
    val firstUrl: String
)