package kz.petprojects.mechta.data.network.response.getSmartphones

import com.google.gson.annotations.SerializedName

data class MetaTags(
    val canonical: Any,
    val description: String,
    val keywords: String,
    val noindex: Any,
    @SerializedName("og:image")
    val og_image: String,
    @SerializedName("og:image:height")
    val og_image_height: String,
    @SerializedName("og:image:width")
    val og_image_width: String,
    @SerializedName("og:title")
    val og_title: String,
    @SerializedName("og:type")
    val og_type: String,
    val title: String
)