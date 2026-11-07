package sa.factory.rawasiproptechreal.domain.repository

import sa.factory.rawasiproptechreal.model.RawasiProptechItem
import sa.factory.rawasiproptechreal.model.RawasiProptechOrder
import sa.factory.rawasiproptechreal.model.RawasiProptechTelemetry
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface RawasiProptechRepository {
    val catalogFlow: Flow<List<RawasiProptechItem>>
    val activeCart: StateFlow<List<RawasiProptechItem>>
    val telemetryFlow: Flow<RawasiProptechTelemetry>
    
    fun addToCart(item: RawasiProptechItem)
    fun removeFromCart(item: RawasiProptechItem)
    fun clearCart()
    suspend fun checkout(paymentMethod: String): RawasiProptechOrder
    suspend fun refreshCatalog()
}
