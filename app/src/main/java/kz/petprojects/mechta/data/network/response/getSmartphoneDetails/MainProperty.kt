package kz.petprojects.mechta.data.network.response.getSmartphoneDetails

data class MainProperty(
    val code: String,
    val prop_id: Int,
    val prop_name: String,
    val prop_name_description: String,
    val prop_value: String,
    val section_code: Any
)