package kz.petprojects.mechta.data.network.response.getSmartphoneDetails

data class GetSmartphoneDetailsResponse(
    val `data`: Data,
    val errors: List<Any>,
    val result: Boolean
)