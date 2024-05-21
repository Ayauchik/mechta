package kz.petprojects.mechta.data.network.response.getSmartphoneDetails

import com.google.gson.annotations.SerializedName

data class MetaTags(
    @SerializedName("description")
    val description: String,
    val keywords: String,
    @SerializedName("og:description")
    val ogDescription: String,
    @SerializedName("og:image")
    val ogImage: String,
    @SerializedName("og:image:height")
    val ogImageHeight: String,
    @SerializedName("og:image:width")
    val ogImageWidth: String,
    @SerializedName("og:site_name")
    val ogSite_name: String,
    @SerializedName("og:title")
    val ogTitle: String,
    @SerializedName("og:type")
    val ogType: String,
    val title: String
)