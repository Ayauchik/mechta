package kz.petprojects.mechta.ui.navigation

object Destinations {
    const val main = "main"

    object SmartphoneDetails {
        const val route = "pokemonDetails/{code}"
        const val codeArg = "code"

        fun createRoute (code: String): String {
            return "pokemonDetails/$code"
        }
    }

}