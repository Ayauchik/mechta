package kz.petprojects.mechta.data.network.mapper.getSmartphones

import kz.petprojects.mechta.data.network.response.getSmartphones.Item
import kz.petprojects.mechta.domain.model.Smartphone

class ItemMapper {
    fun fromRemoteToDomain(item: Item): Smartphone {
        return Smartphone(
            id = item.id,
            name = item.name,
            code = item.code,
            price = item.price,
            bonus = item.bonus,
            photos = item.photos,
            isFavourite = false,
            inFavourites = item.in_favorites
        )
    }
}