package sa.factory.rawasiproptechreal.di

import sa.factory.rawasiproptechreal.data.repository.RawasiProptechRepositoryImpl
import sa.factory.rawasiproptechreal.domain.repository.RawasiProptechRepository
import sa.factory.rawasiproptechreal.domain.usecase.*
import sa.factory.rawasiproptechreal.hardware.HardwareSensorDriver
import sa.factory.rawasiproptechreal.ui.viewmodel.RawasiProptechViewModel

class DiContainer {
    val sensorDriver = HardwareSensorDriver()
    val repository: RawasiProptechRepository = RawasiProptechRepositoryImpl(sensorDriver)

    val getCatalogUseCase = GetCatalogUseCase(repository)
    val manageCartUseCase = ManageCartUseCase(repository)
    val processCheckoutUseCase = ProcessCheckoutUseCase(repository)
    val observeHardwareTelemetryUseCase = ObserveHardwareTelemetryUseCase(repository)

    fun provideViewModel(): RawasiProptechViewModel = RawasiProptechViewModel(
        getCatalogUseCase,
        manageCartUseCase,
        processCheckoutUseCase,
        observeHardwareTelemetryUseCase
    )
}
