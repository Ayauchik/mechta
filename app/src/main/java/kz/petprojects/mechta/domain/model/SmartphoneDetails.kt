package kz.petprojects.mechta.domain.model

data class SmartphoneDetails(
    val code: String,
    val name: String,
    val photos: List<String>,
    val rating: Any,
    val reviewsCount: Int,
    var inFavourites: Boolean,
   // val sameProductProperties: List<String>,
    val mainProperties: List<Properties>
)
