package com.lalamove.clone.feature.home.view

import androidx.compose.animation.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.lalamove.clone.feature.home.entity.RouteStop
import com.lalamove.clone.feature.home.entity.StopType
import com.lalamove.clone.theme.*

@Composable
fun RouteCard(
        stops: List<RouteStop>,
        scheduleMode: String,
        pickupAddress: String,
        onAddStop: () -> Unit,
        onRemoveStop: (String) -> Unit,
        modifier: Modifier = Modifier
) {
    Card(
            modifier = modifier.fillMaxWidth().padding(horizontal = 16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = NeutralWhite),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            stops.forEachIndexed { index, stop ->
                RouteStopRow(
                        stop = stop,
                        scheduleMode = if (index == 0) scheduleMode else null,
                        isFirst = index == 0,
                        isLast = index == stops.lastIndex,
                        showRemove = stop.type == StopType.MID_STOP,
                        onRemove = { onRemoveStop(stop.id) }
                )

                if (index < stops.lastIndex) {
                    // Dotted line connector
                    Row(modifier = Modifier.height(24.dp)) {
                        Spacer(Modifier.width(11.dp))
                        Canvas(modifier = Modifier.width(2.dp).fillMaxHeight()) {
                            val pathEffect = PathEffect.dashPathEffect(floatArrayOf(6f, 4f), 0f)
                            drawLine(
                                    color = Color(0xFFBDBDBD),
                                    start = Offset(size.width / 2, 0f),
                                    end = Offset(size.width / 2, size.height),
                                    strokeWidth = 2f,
                                    pathEffect = pathEffect
                            )
                        }
                    }

                    if (index < stops.lastIndex - 1 || index == 0) {
                        HorizontalDivider(
                                modifier = Modifier.padding(start = 36.dp),
                                color = NeutralGray200,
                                thickness = 0.5.dp
                        )
                    }
                }
            }

            HorizontalDivider(
                    modifier = Modifier.padding(top = 8.dp),
                    color = NeutralGray200,
                    thickness = 0.5.dp
            )

            // Add Stop button
            Row(
                    modifier =
                            Modifier.fillMaxWidth()
                                    .clickable { onAddStop() }
                                    .padding(vertical = 12.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                        Icons.Default.Add,
                        contentDescription = "Add stop",
                        modifier = Modifier.size(20.dp),
                        tint = NeutralGray600
                )
                Spacer(Modifier.width(8.dp))
                Text(
                        text = "Add Stop",
                        style = MaterialTheme.typography.titleSmall,
                        color = NeutralGray600
                )
            }
        }
    }
}

@Composable
private fun RouteStopRow(
        stop: RouteStop,
        scheduleMode: String?,
        isFirst: Boolean,
        isLast: Boolean,
        showRemove: Boolean,
        onRemove: () -> Unit
) {
    Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
    ) {
        // Route indicator icon
        Box(modifier = Modifier.size(24.dp), contentAlignment = Alignment.Center) {
            when (stop.type) {
                StopType.PICKUP -> {
                    // Orange circle outline
                    Box(
                            modifier =
                                    Modifier.size(14.dp)
                                            .clip(CircleShape)
                                            .background(Color.Transparent)
                    ) {
                        Canvas(modifier = Modifier.fillMaxSize()) {
                            drawCircle(
                                    color = Color(0xFFF76B1C),
                                    radius = size.minDimension / 2,
                                    style =
                                            androidx.compose.ui.graphics.drawscope.Stroke(
                                                    width = 3f
                                            )
                            )
                        }
                    }
                }
                StopType.MID_STOP -> {
                    // Smaller orange circle outline
                    Box(modifier = Modifier.size(12.dp).clip(CircleShape)) {
                        Canvas(modifier = Modifier.fillMaxSize()) {
                            drawCircle(
                                    color = Color(0xFFF76B1C),
                                    radius = size.minDimension / 2,
                                    style =
                                            androidx.compose.ui.graphics.drawscope.Stroke(
                                                    width = 2.5f
                                            )
                            )
                        }
                    }
                }
                StopType.DROP_OFF -> {
                    Icon(
                            Icons.Default.LocationOn,
                            contentDescription = "Drop-off",
                            modifier = Modifier.size(20.dp),
                            tint = LalamoveOrange
                    )
                }
            }
        }

        Spacer(Modifier.width(12.dp))

        // Label
        Text(
                text =
                        stop.label.ifEmpty {
                            when (stop.type) {
                                StopType.PICKUP -> "Pickup location"
                                StopType.MID_STOP -> "Mid-stop location"
                                StopType.DROP_OFF -> "Drop-off location"
                            }
                        },
                style = MaterialTheme.typography.bodyLarge,
                color = if (stop.label.isEmpty()) NeutralGray400 else NeutralGray900,
                modifier = Modifier.weight(1f)
        )

        // Schedule mode or remove button
        if (scheduleMode != null) {
            Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier =
                            Modifier.clip(RoundedCornerShape(8.dp))
                                    .clickable {}
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                        text = scheduleMode,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium,
                        color = NeutralGray800
                )
                Icon(
                        Icons.Default.KeyboardArrowDown,
                        contentDescription = "Schedule",
                        modifier = Modifier.size(20.dp),
                        tint = NeutralGray600
                )
            }
        }

        if (showRemove) {
            IconButton(onClick = onRemove, modifier = Modifier.size(32.dp)) {
                Icon(
                        Icons.Default.Close,
                        contentDescription = "Remove stop",
                        modifier = Modifier.size(18.dp),
                        tint = NeutralGray400
                )
            }
        }
    }
}
