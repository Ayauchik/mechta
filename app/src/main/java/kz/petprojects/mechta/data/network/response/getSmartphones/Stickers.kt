package kz.petprojects.mechta.data.network.response.getSmartphones

import com.google.gson.annotations.SerializedName

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.lang.reflect.Type

sealed class Stickers {
    data class SingleSticker(val id: Int, val name: String) : Stickers()
    data class StickerList(val stickers: List<SingleSticker>) : Stickers()
}

