package kz.petprojects.mechta.data.network.response.getSmartphoneDetails

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type


class StickersDeserializerForDetailed : JsonDeserializer<StickersForDetailed> {
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): StickersForDetailed {
        return if (json.isJsonArray) {
            val stickersForDetailed = json.asJsonArray.map { context.deserialize<StickersForDetailed.SingleSticker>(it, StickersForDetailed.SingleSticker::class.java) }
            StickersForDetailed.StickerList(stickersForDetailed)
        } else {
            context.deserialize(json, StickersForDetailed.SingleSticker::class.java)
        }
    }
}