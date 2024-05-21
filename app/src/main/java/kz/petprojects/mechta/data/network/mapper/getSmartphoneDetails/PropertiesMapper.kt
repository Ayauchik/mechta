package kz.petprojects.mechta.data.network.mapper.getSmartphoneDetails

import kz.petprojects.mechta.data.network.response.getSmartphoneDetails.MainProperty
import kz.petprojects.mechta.domain.model.Properties

class PropertiesMapper {
    fun fromRemoteToDomain(mainProperties: MainProperty): Properties {
        return Properties(
            promName = mainProperties.prop_name,
            promNameDescription = mainProperties.prop_name_description,
            propValue = mainProperties.prop_value,
            propId = mainProperties.prop_id.toLong(),
            code = mainProperties.code
        )
    }
}