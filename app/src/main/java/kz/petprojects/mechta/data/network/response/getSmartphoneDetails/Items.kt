package kz.petprojects.mechta.data.network.response.getSmartphoneDetails

import com.google.gson.annotations.SerializedName

data class Items(
    @SerializedName("голубой")
    val blue: Blue
)