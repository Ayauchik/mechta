package kz.petprojects.mechta.data.network.response.getSmartphoneDetails


sealed class StickersForDetailed {
    data class SingleSticker(val id: Int, val name: String) : StickersForDetailed()
    data class StickerList(val stickers: List<Any>) : StickersForDetailed()
}