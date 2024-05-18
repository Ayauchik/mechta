package kz.petprojects.mechta.data.network.response.getSmartphones

data class GetSmartphonesResponse(
    val `data`: Data,
    val errors: List<Any>,
    val result: Boolean
)