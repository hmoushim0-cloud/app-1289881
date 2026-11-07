package sa.factory.rawasiproptechreal.data.remote

import sa.factory.rawasiproptechreal.model.RawasiProptechItem
import sa.factory.rawasiproptechreal.model.RawasiProptechOrder

interface RawasiProptechApiService {
    suspend fun fetchCatalog(): List<RawasiProptechItem>
    suspend fun submitOrder(order: RawasiProptechOrder): Boolean
}
