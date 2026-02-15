package com.lalamove.clone.feature.home.presenter

import androidx.lifecycle.ViewModel
import com.lalamove.clone.feature.home.entity.*
import com.lalamove.clone.feature.home.interactor.HomeInteractor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * VIPER Presenter — manages UI state and processes user events for Home. Delegates business logic
 * to HomeInteractor.
 */
class HomePresenter : ViewModel() {

    private val interactor = HomeInteractor()

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        _uiState.update {
            it.copy(
                    promoBanners = interactor.loadPromoBanners(),
                    vehicles = interactor.loadVehicles(),
                    additionalServices = interactor.loadAdditionalServices()
            )
        }
    }

    fun onVehicleSelected(vehicleId: String) {
        _uiState.update { it.copy(selectedVehicleId = vehicleId) }
    }

    fun onAddStop() {
        _uiState.update { state ->
            val currentStops = state.stops.toMutableList()
            val newMidStop =
                    RouteStop(id = "mid_${currentStops.size}", label = "", type = StopType.MID_STOP)
            // Insert before the last stop (DROP_OFF)
            currentStops.add(currentStops.size - 1, newMidStop)
            state.copy(stops = currentStops)
        }
    }

    fun onRemoveStop(stopId: String) {
        _uiState.update { state -> state.copy(stops = state.stops.filter { it.id != stopId }) }
    }

    fun onPickupAddressChanged(address: String) {
        _uiState.update { it.copy(pickupAddress = address) }
    }

    fun onStopLabelChanged(stopId: String, label: String) {
        _uiState.update { state ->
            state.copy(
                    stops = state.stops.map { if (it.id == stopId) it.copy(label = label) else it }
            )
        }
    }
}
