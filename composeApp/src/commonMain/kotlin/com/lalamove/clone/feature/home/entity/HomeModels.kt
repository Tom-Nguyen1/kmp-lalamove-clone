package com.lalamove.clone.feature.home.entity

import kotlinx.serialization.Serializable

@Serializable
data class PromoBanner(
        val id: String,
        val title: String,
        val subtitle: String,
        val backgroundColor: Long = 0xFFD4534E
)

@Serializable data class RouteStop(val id: String, val label: String, val type: StopType)

@Serializable
enum class StopType {
    PICKUP,
    MID_STOP,
    DROP_OFF
}

@Serializable
data class Vehicle(
        val id: String,
        val name: String,
        val description: String = "",
        val dimensions: String = "",
        val maxWeight: String = "",
        val iconName: String = ""
)

@Serializable data class AdditionalService(val id: String, val name: String, val price: String)

data class HomeUiState(
        val promoBanners: List<PromoBanner> = emptyList(),
        val stops: List<RouteStop> =
                listOf(RouteStop("1", "", StopType.PICKUP), RouteStop("2", "", StopType.DROP_OFF)),
        val vehicles: List<Vehicle> = emptyList(),
        val selectedVehicleId: String? = null,
        val additionalServices: List<AdditionalService> = emptyList(),
        val scheduleMode: String = "Now",
        val pickupAddress: String = ""
)
