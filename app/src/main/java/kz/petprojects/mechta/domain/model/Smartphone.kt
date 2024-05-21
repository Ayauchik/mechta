package kz.petprojects.mechta.domain.model

data class Smartphone(
    val id: Int,
    val name: String,
    val code: String,
    val price: Int,
    val bonus: Int,
    val photos: List<String>,
    var isFavourite: Boolean,
    val inFavourites: Boolean // noted after
)
