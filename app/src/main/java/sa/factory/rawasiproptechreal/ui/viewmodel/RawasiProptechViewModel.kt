package sa.factory.rawasiproptechreal.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import sa.factory.rawasiproptechreal.domain.usecase.*
import sa.factory.rawasiproptechreal.model.RawasiProptechItem
import sa.factory.rawasiproptechreal.model.RawasiProptechOrder
import sa.factory.rawasiproptechreal.model.RawasiProptechTelemetry
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class RawasiProptechUiState(
    val catalog: List<RawasiProptechItem> = emptyList(),
    val filteredCatalog: List<RawasiProptechItem> = emptyList(),
    val selectedCategory: String = "الكل",
    val cart: List<RawasiProptechItem> = emptyList(),
    val telemetry: RawasiProptechTelemetry? = null,
    val lastOrder: RawasiProptechOrder? = null,
    val isCheckingOut: Boolean = false,
    val activeTab: String = "home"
)

class RawasiProptechViewModel(
    private val getCatalogUseCase: GetCatalogUseCase,
    private val manageCartUseCase: ManageCartUseCase,
    private val processCheckoutUseCase: ProcessCheckoutUseCase,
    private val observeHardwareTelemetryUseCase: ObserveHardwareTelemetryUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(RawasiProptechUiState())
    val uiState: StateFlow<RawasiProptechUiState> = _uiState.asStateFlow()

    init {
        // Observe Catalog
        getCatalogUseCase().onEach { list ->
            _uiState.update { it.copy(catalog = list, filteredCatalog = list) }
        }.launchIn(viewModelScope)

        // Observe Cart
        manageCartUseCase.cart.onEach { cartItems ->
            _uiState.update { it.copy(cart = cartItems) }
        }.launchIn(viewModelScope)

        // Observe Telemetry
        observeHardwareTelemetryUseCase().onEach { telem ->
            _uiState.update { it.copy(telemetry = telem) }
        }.launchIn(viewModelScope)
    }

    fun selectCategory(category: String) {
        _uiState.update { state ->
            val filtered = if (category == "الكل") state.catalog else state.catalog.filter { it.category == category }
            state.copy(selectedCategory = category, filteredCatalog = filtered)
        }
    }

    fun switchTab(tab: String) {
        _uiState.update { it.copy(activeTab = tab) }
    }

    fun addToCart(item: RawasiProptechItem) = manageCartUseCase.add(item)
    fun removeFromCart(item: RawasiProptechItem) = manageCartUseCase.remove(item)

    fun checkout() {
        viewModelScope.launch {
            _uiState.update { it.copy(isCheckingOut = true) }
            val order = processCheckoutUseCase()
            _uiState.update { it.copy(isCheckingOut = false, lastOrder = order, activeTab = "checkout") }
        }
    }
}
