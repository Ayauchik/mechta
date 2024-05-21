package kz.petprojects.mechta.data.network.response.getSmartphoneDetails

data class Prices(
    val base_price: Int,
    val discounted_price: Int,
    val has_discount: Boolean
)