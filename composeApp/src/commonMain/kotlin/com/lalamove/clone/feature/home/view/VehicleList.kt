package com.lalamove.clone.feature.home.view

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.lalamove.clone.feature.home.entity.Vehicle
import com.lalamove.clone.theme.*

@Composable
fun VehicleList(
        vehicles: List<Vehicle>,
        selectedVehicleId: String?,
        onVehicleSelected: (String) -> Unit,
        modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(horizontal = 16.dp)) {
        Text(
                text = "Available vehicles",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = NeutralGray900,
                modifier = Modifier.padding(bottom = 12.dp)
        )

        vehicles.forEach { vehicle ->
            VehicleCard(
                    vehicle = vehicle,
                    isSelected = vehicle.id == selectedVehicleId,
                    onClick = { onVehicleSelected(vehicle.id) }
            )
            Spacer(Modifier.height(12.dp))
        }
    }
}

@Composable
fun VehicleCard(
        vehicle: Vehicle,
        isSelected: Boolean,
        onClick: () -> Unit,
        modifier: Modifier = Modifier
) {
    val borderColor by
            animateColorAsState(
                    targetValue = if (isSelected) LalamoveOrange else NeutralGray200,
                    animationSpec = tween(200)
            )

    Card(
            modifier = modifier.fillMaxWidth().clickable { onClick() },
            shape = RoundedCornerShape(12.dp),
            colors =
                    CardDefaults.cardColors(
                            containerColor = if (isSelected) LalamoveOrangeSurface else NeutralWhite
                    ),
            border = BorderStroke(width = if (isSelected) 2.dp else 1.dp, color = borderColor),
            elevation =
                    CardDefaults.cardElevation(defaultElevation = if (isSelected) 2.dp else 0.dp)
    ) {
        Box {
            Row(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
            ) {
                // Vehicle icon placeholder — drawn with Canvas
                VehicleIcon(iconName = vehicle.iconName, modifier = Modifier.size(64.dp))

                Spacer(Modifier.width(16.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                            text = vehicle.name,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold,
                            color = NeutralGray900
                    )

                    if (isSelected && vehicle.description.isNotEmpty()) {
                        Spacer(Modifier.height(4.dp))
                        Text(
                                text = vehicle.description,
                                style = MaterialTheme.typography.bodySmall,
                                color = NeutralGray600
                        )
                        if (vehicle.dimensions.isNotEmpty()) {
                            Spacer(Modifier.height(4.dp))
                            Text(
                                    text = "📦 ${vehicle.dimensions} • ${vehicle.maxWeight}",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = NeutralGray600
                            )
                        }
                    }
                }
            }

            // Selection checkmark
            if (isSelected) {
                Icon(
                        Icons.Default.CheckCircle,
                        contentDescription = "Selected",
                        tint = LalamoveOrange,
                        modifier = Modifier.align(Alignment.TopEnd).padding(12.dp).size(24.dp)
                )
            }
        }
    }
}

@Composable
private fun VehicleIcon(iconName: String, modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        when (iconName) {
            "courier" -> {
                // Scooter-like shape
                drawRoundRect(
                        color = Color(0xFFF76B1C),
                        topLeft = androidx.compose.ui.geometry.Offset(w * 0.15f, h * 0.2f),
                        size = androidx.compose.ui.geometry.Size(w * 0.5f, h * 0.4f),
                        cornerRadius = CornerRadius(8f)
                )
                drawCircle(
                        color = Color(0xFF424242),
                        radius = w * 0.12f,
                        center = androidx.compose.ui.geometry.Offset(w * 0.25f, h * 0.75f)
                )
                drawCircle(
                        color = Color(0xFF424242),
                        radius = w * 0.12f,
                        center = androidx.compose.ui.geometry.Offset(w * 0.7f, h * 0.75f)
                )
                // Package on back
                drawRoundRect(
                        color = Color(0xFFE05A0C),
                        topLeft = androidx.compose.ui.geometry.Offset(w * 0.55f, h * 0.15f),
                        size = androidx.compose.ui.geometry.Size(w * 0.3f, h * 0.35f),
                        cornerRadius = CornerRadius(4f)
                )
            }
            "car" -> {
                // Car body
                drawRoundRect(
                        color = Color(0xFFF76B1C),
                        topLeft = androidx.compose.ui.geometry.Offset(w * 0.05f, h * 0.3f),
                        size = androidx.compose.ui.geometry.Size(w * 0.9f, h * 0.35f),
                        cornerRadius = CornerRadius(12f)
                )
                // Car roof
                drawRoundRect(
                        color = Color(0xFFE05A0C),
                        topLeft = androidx.compose.ui.geometry.Offset(w * 0.2f, h * 0.12f),
                        size = androidx.compose.ui.geometry.Size(w * 0.55f, h * 0.25f),
                        cornerRadius = CornerRadius(10f)
                )
                // Wheels
                drawCircle(
                        color = Color(0xFF424242),
                        radius = w * 0.1f,
                        center = androidx.compose.ui.geometry.Offset(w * 0.25f, h * 0.72f)
                )
                drawCircle(
                        color = Color(0xFF424242),
                        radius = w * 0.1f,
                        center = androidx.compose.ui.geometry.Offset(w * 0.75f, h * 0.72f)
                )
            }
            "mpv" -> {
                // MPV/SUV body
                drawRoundRect(
                        color = Color(0xFFF76B1C),
                        topLeft = androidx.compose.ui.geometry.Offset(w * 0.05f, h * 0.25f),
                        size = androidx.compose.ui.geometry.Size(w * 0.9f, h * 0.4f),
                        cornerRadius = CornerRadius(8f)
                )
                drawRoundRect(
                        color = Color(0xFFE05A0C),
                        topLeft = androidx.compose.ui.geometry.Offset(w * 0.1f, h * 0.1f),
                        size = androidx.compose.ui.geometry.Size(w * 0.7f, h * 0.25f),
                        cornerRadius = CornerRadius(8f)
                )
                drawCircle(
                        color = Color(0xFF424242),
                        radius = w * 0.11f,
                        center = androidx.compose.ui.geometry.Offset(w * 0.25f, h * 0.72f)
                )
                drawCircle(
                        color = Color(0xFF424242),
                        radius = w * 0.11f,
                        center = androidx.compose.ui.geometry.Offset(w * 0.75f, h * 0.72f)
                )
            }
            "van" -> {
                // Van body
                drawRoundRect(
                        color = Color(0xFFF76B1C),
                        topLeft = androidx.compose.ui.geometry.Offset(w * 0.05f, h * 0.2f),
                        size = androidx.compose.ui.geometry.Size(w * 0.9f, h * 0.45f),
                        cornerRadius = CornerRadius(6f)
                )
                drawRoundRect(
                        color = Color(0xFFFF9B59),
                        topLeft = androidx.compose.ui.geometry.Offset(w * 0.6f, h * 0.2f),
                        size = androidx.compose.ui.geometry.Size(w * 0.3f, h * 0.3f),
                        cornerRadius = CornerRadius(4f)
                )
                drawCircle(
                        color = Color(0xFF424242),
                        radius = w * 0.1f,
                        center = androidx.compose.ui.geometry.Offset(w * 0.22f, h * 0.72f)
                )
                drawCircle(
                        color = Color(0xFF424242),
                        radius = w * 0.1f,
                        center = androidx.compose.ui.geometry.Offset(w * 0.78f, h * 0.72f)
                )
            }
            "lorry" -> {
                // Lorry cargo
                drawRoundRect(
                        color = Color(0xFFF76B1C),
                        topLeft = androidx.compose.ui.geometry.Offset(w * 0.02f, h * 0.15f),
                        size = androidx.compose.ui.geometry.Size(w * 0.55f, h * 0.5f),
                        cornerRadius = CornerRadius(4f)
                )
                // Lorry cabin
                drawRoundRect(
                        color = Color(0xFFE05A0C),
                        topLeft = androidx.compose.ui.geometry.Offset(w * 0.6f, h * 0.25f),
                        size = androidx.compose.ui.geometry.Size(w * 0.35f, h * 0.4f),
                        cornerRadius = CornerRadius(6f)
                )
                drawCircle(
                        color = Color(0xFF424242),
                        radius = w * 0.1f,
                        center = androidx.compose.ui.geometry.Offset(w * 0.2f, h * 0.75f)
                )
                drawCircle(
                        color = Color(0xFF424242),
                        radius = w * 0.1f,
                        center = androidx.compose.ui.geometry.Offset(w * 0.45f, h * 0.75f)
                )
                drawCircle(
                        color = Color(0xFF424242),
                        radius = w * 0.1f,
                        center = androidx.compose.ui.geometry.Offset(w * 0.8f, h * 0.75f)
                )
            }
            else -> {
                drawRoundRect(color = Color(0xFFEEEEEE), cornerRadius = CornerRadius(8f))
            }
        }
    }
}
