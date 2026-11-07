package sa.factory.rawasiproptechreal.domain.usecase

import sa.factory.rawasiproptechreal.domain.repository.RawasiProptechRepository
import sa.factory.rawasiproptechreal.model.RawasiProptechItem
import sa.factory.rawasiproptechreal.model.RawasiProptechOrder
import sa.factory.rawasiproptechreal.model.RawasiProptechTelemetry
import kotlinx.coroutines.flow.Flow

class GetCatalogUseCase(private val repository: RawasiProptechRepository) {
    operator fun invoke(): Flow<List<RawasiProptechItem>> = repository.catalogFlow
}

class ManageCartUseCase(private val repository: RawasiProptechRepository) {
    val cart = repository.activeCart
    fun add(item: RawasiProptechItem) = repository.addToCart(item)
    fun remove(item: RawasiProptechItem) = repository.removeFromCart(item)
    fun clear() = repository.clearCart()
}

class ProcessCheckoutUseCase(private val repository: RawasiProptechRepository) {
    suspend operator fun invoke(paymentMethod: String = "Mada Contactless EMV"): RawasiProptechOrder =
        repository.checkout(paymentMethod)
}

class ObserveHardwareTelemetryUseCase(private val repository: RawasiProptechRepository) {
    operator fun invoke(): Flow<RawasiProptechTelemetry> = repository.telemetryFlow
}
