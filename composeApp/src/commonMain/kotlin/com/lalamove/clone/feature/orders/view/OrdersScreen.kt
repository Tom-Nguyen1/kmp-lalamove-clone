package com.lalamove.clone.feature.orders.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.lalamove.clone.theme.*

data class OrderItem(
        val id: String,
        val status: String,
        val statusColor: Color,
        val pickup: String,
        val dropoff: String,
        val vehicleType: String,
        val price: String,
        val date: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrdersScreen(onBack: () -> Unit, modifier: Modifier = Modifier) {
    val sampleOrders =
            listOf(
                    OrderItem(
                            id = "LAL-2026-4521",
                            status = "Completed",
                            statusColor = SuccessGreen,
                            pickup = "Suntec City, Singapore",
                            dropoff = "Marina Bay Sands",
                            vehicleType = "Courier",
                            price = "S\$8.50",
                            date = "Feb 14, 2026"
                    ),
                    OrderItem(
                            id = "LAL-2026-4520",
                            status = "Completed",
                            statusColor = SuccessGreen,
                            pickup = "Long John Silver's, hakim",
                            dropoff = "Orchard Road",
                            vehicleType = "Car",
                            price = "S\$15.00",
                            date = "Feb 13, 2026"
                    ),
                    OrderItem(
                            id = "LAL-2026-4519",
                            status = "Cancelled",
                            statusColor = ErrorRed,
                            pickup = "Changi Airport T3",
                            dropoff = "Jurong East",
                            vehicleType = "MPV",
                            price = "S\$28.00",
                            date = "Feb 12, 2026"
                    ),
                    OrderItem(
                            id = "LAL-2026-4518",
                            status = "Completed",
                            statusColor = SuccessGreen,
                            pickup = "Ion Orchard",
                            dropoff = "Raffles Place",
                            vehicleType = "Courier",
                            price = "S\$6.00",
                            date = "Feb 11, 2026"
                    )
            )

    Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                        title = {
                            Text(
                                    text = "Orders",
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.SemiBold
                            )
                        },
                        navigationIcon = {
                            IconButton(onClick = onBack) {
                                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
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
        Column(
                modifier =
                        modifier.fillMaxSize()
                                .padding(padding)
                                .verticalScroll(rememberScrollState())
                                .padding(16.dp)
        ) {
            sampleOrders.forEach { order ->
                OrderCard(order = order)
                Spacer(Modifier.height(12.dp))
            }
        }
    }
}

@Composable
private fun OrderCard(order: OrderItem) {
    Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = NeutralWhite),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            // Header
            Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                        text = order.id,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.SemiBold,
                        color = NeutralGray900
                )
                Box(
                        modifier =
                                Modifier.clip(RoundedCornerShape(6.dp))
                                        .background(order.statusColor.copy(alpha = 0.12f))
                                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                            text = order.status,
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.SemiBold,
                            color = order.statusColor
                    )
                }
            }

            Spacer(Modifier.height(12.dp))

            // Route
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                        Icons.Default.RadioButtonChecked,
                        contentDescription = null,
                        modifier = Modifier.size(14.dp),
                        tint = LalamoveOrange
                )
                Spacer(Modifier.width(8.dp))
                Text(
                        text = order.pickup,
                        style = MaterialTheme.typography.bodySmall,
                        color = NeutralGray800
                )
            }

            Spacer(Modifier.height(4.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                        Icons.Default.LocationOn,
                        contentDescription = null,
                        modifier = Modifier.size(14.dp),
                        tint = LalamoveOrange
                )
                Spacer(Modifier.width(8.dp))
                Text(
                        text = order.dropoff,
                        style = MaterialTheme.typography.bodySmall,
                        color = NeutralGray800
                )
            }

            Spacer(Modifier.height(12.dp))
            HorizontalDivider(color = NeutralGray200, thickness = 0.5.dp)
            Spacer(Modifier.height(12.dp))

            // Footer
            Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                            Icons.Default.LocalShipping,
                            contentDescription = null,
                            modifier = Modifier.size(14.dp),
                            tint = NeutralGray400
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(
                            text = order.vehicleType,
                            style = MaterialTheme.typography.labelSmall,
                            color = NeutralGray600
                    )
                    Spacer(Modifier.width(12.dp))
                    Text(
                            text = order.date,
                            style = MaterialTheme.typography.labelSmall,
                            color = NeutralGray400
                    )
                }
                Text(
                        text = order.price,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = NeutralGray900
                )
            }
        }
    }
}
