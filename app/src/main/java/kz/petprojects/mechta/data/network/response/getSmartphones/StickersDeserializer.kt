package kz.petprojects.mechta.data.network.response.getSmartphones

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class StickersDeserializer : JsonDeserializer<Stickers> {
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Stickers {
        return if (json.isJsonArray) {
            val stickers = json.asJsonArray.map { context.deserialize<Stickers.SingleSticker>(it, Stickers.SingleSticker::class.java) }
            Stickers.StickerList(stickers)
        } else {
            context.deserialize(json, Stickers.SingleSticker::class.java)
        }
    }
}
