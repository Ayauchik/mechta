package kz.petprojects.mechta.data.network.response.getSmartphoneDetails

data class Shop(
    val coordinates: Coordinates,
    val items_count: Double,
    val items_count_real: Int,
    val shop_name: String,
    val work_time: String,
    val work_time_short: String
)