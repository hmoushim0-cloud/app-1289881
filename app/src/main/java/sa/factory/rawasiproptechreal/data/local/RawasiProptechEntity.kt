package sa.factory.rawasiproptechreal.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import sa.factory.rawasiproptechreal.model.RawasiProptechItem

@Entity(tableName = "real_estate_items_cache")
data class RawasiProptechEntity(
    @PrimaryKey val id: String,
    val title: String,
    val description: String,
    val priceSar: Double,
    val badge: String,
    val category: String,
    val status: String
) {
    fun toDomain(): RawasiProptechItem = RawasiProptechItem(
        id = id,
        title = title,
        description = description,
        priceSar = priceSar,
        badge = badge,
        category = category,
        status = status
    )
}
