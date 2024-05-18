package kz.petprojects.mechta.data.network.response.getSmartphones

data class Data(
    val all_items_count: Int,
    val breadcrumbs: List<Breadcrumb>,
    val header: String,
    val index: Boolean,
    val items: List<Item>,
    val meta_tags: MetaTags,
    val page_items_count: Int,
    val page_number: Int,
    val section_1c_code: String,
    val section_description: String,
    val section_id: Int,
    val section_list: List<Section>
)