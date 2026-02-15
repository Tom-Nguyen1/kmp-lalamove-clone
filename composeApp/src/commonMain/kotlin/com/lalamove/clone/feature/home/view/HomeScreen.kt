package com.lalamove.clone.feature.home.view

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lalamove.clone.feature.home.presenter.HomePresenter
import com.lalamove.clone.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
        onMenuClick: () -> Unit,
        onNotificationsClick: () -> Unit,
        modifier: Modifier = Modifier
) {
    val presenter: HomePresenter = viewModel { HomePresenter() }
    val uiState by presenter.uiState.collectAsState()

    val scrollState = rememberScrollState()

    Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                        title = {
                            Text(
                                    text = "LALAMOVE",
                                    style = MaterialTheme.typography.headlineMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = LalamoveOrange,
                                    letterSpacing =
                                            MaterialTheme.typography.headlineMedium.letterSpacing
                            )
                        },
                        navigationIcon = {
                            IconButton(onClick = onMenuClick) {
                                Icon(
                                        Icons.Default.Menu,
                                        contentDescription = "Menu",
                                        tint = NeutralGray800
                                )
                            }
                        },
                        actions = {
                            BadgedBox(
                                    badge = {
                                        Badge(
                                                containerColor = LalamoveOrange,
                                                contentColor = NeutralWhite
                                        ) { Text("7") }
                                    }
                            ) {
                                IconButton(onClick = onNotificationsClick) {
                                    Icon(
                                            Icons.Default.Notifications,
                                            contentDescription = "Notifications",
                                            tint = NeutralGray800
                                    )
                                }
                            }
                        },
                        colors =
                                TopAppBarDefaults.centerAlignedTopAppBarColors(
                                        containerColor = NeutralWhite
                                )
                )
            },
            containerColor = NeutralGray100
    ) { padding ->
        Column(modifier = modifier.fillMaxSize().padding(padding).verticalScroll(scrollState)) {
            Spacer(Modifier.height(8.dp))

            // Promo Carousel
            PromoCarousel(banners = uiState.promoBanners, modifier = Modifier.fillMaxWidth())

            Spacer(Modifier.height(20.dp))

            // Route Card
            RouteCard(
                    stops = uiState.stops,
                    scheduleMode = uiState.scheduleMode,
                    pickupAddress = uiState.pickupAddress,
                    onAddStop = { presenter.onAddStop() },
                    onRemoveStop = { presenter.onRemoveStop(it) }
            )

            Spacer(Modifier.height(24.dp))

            // Vehicle List
            VehicleList(
                    vehicles = uiState.vehicles,
                    selectedVehicleId = uiState.selectedVehicleId,
                    onVehicleSelected = { presenter.onVehicleSelected(it) }
            )

            // Additional Services — shown when a vehicle is selected
            AnimatedVisibility(
                    visible = uiState.selectedVehicleId != null,
                    enter = fadeIn() + expandVertically(),
                    exit = fadeOut() + shrinkVertically()
            ) {
                Column {
                    Spacer(Modifier.height(24.dp))
                    AdditionalServicesSection(services = uiState.additionalServices)
                }
            }

            Spacer(Modifier.height(100.dp))
        }
    }
}
