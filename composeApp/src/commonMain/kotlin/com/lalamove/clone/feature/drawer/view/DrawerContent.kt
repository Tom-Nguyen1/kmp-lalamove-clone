package com.lalamove.clone.feature.drawer.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.lalamove.clone.feature.drawer.entity.DrawerUiState
import com.lalamove.clone.theme.*

@Composable
fun DrawerContent(
        onItemClick: (String) -> Unit,
        onClose: () -> Unit,
        modifier: Modifier = Modifier
) {
    val uiState = DrawerUiState()

    Column(modifier = modifier.fillMaxHeight().width(300.dp).background(NeutralWhite)) {
        // Profile header
        Row(
                modifier =
                        Modifier.fillMaxWidth()
                                .clickable {}
                                .padding(horizontal = 24.dp, vertical = 24.dp),
                verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                    modifier = Modifier.size(48.dp).clip(CircleShape).background(NeutralGray200),
                    contentAlignment = Alignment.Center
            ) {
                Icon(
                        Icons.Default.Person,
                        contentDescription = "Profile",
                        modifier = Modifier.size(28.dp),
                        tint = NeutralGray400
                )
            }

            Spacer(Modifier.width(16.dp))

            Text(
                    text = uiState.userName,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = NeutralGray900,
                    modifier = Modifier.weight(1f)
            )

            Icon(
                    Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "Go to profile",
                    tint = NeutralGray400
            )
        }

        // Menu items
        uiState.menuItems.forEach { item ->
            DrawerMenuRow(
                    icon = getMenuIcon(item.iconName),
                    title = item.title,
                    onClick = { onItemClick(item.id) }
            )
        }

        Spacer(Modifier.weight(1f))

        // Region selector
        HorizontalDivider(color = NeutralGray200)

        Row(
                modifier =
                        Modifier.fillMaxWidth()
                                .clickable {}
                                .padding(horizontal = 24.dp, vertical = 20.dp),
                verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                    text = "You're in ",
                    style = MaterialTheme.typography.bodyLarge,
                    color = NeutralGray600
            )
            Text(
                    text = uiState.region,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = LalamoveOrange
            )
            Spacer(Modifier.weight(1f))
            Icon(
                    Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "Change region",
                    tint = NeutralGray400
            )
        }
    }
}

@Composable
private fun DrawerMenuRow(icon: ImageVector, title: String, onClick: () -> Unit) {
    Row(
            modifier =
                    Modifier.fillMaxWidth()
                            .clickable { onClick() }
                            .padding(horizontal = 24.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
                icon,
                contentDescription = title,
                modifier = Modifier.size(24.dp),
                tint = LalamoveOrange
        )
        Spacer(Modifier.width(20.dp))
        Text(text = title, style = MaterialTheme.typography.bodyLarge, color = NeutralGray900)
    }
}

private fun getMenuIcon(iconName: String): ImageVector =
        when (iconName) {
            "orders" -> Icons.Default.Inventory2
            "wallet" -> Icons.Default.AccountBalanceWallet
            "delivery_form" -> Icons.Default.Description
            "my_drivers" -> Icons.Default.Groups
            "rewards" -> Icons.Default.EmojiEvents
            "coupon_shop" -> Icons.Default.Redeem
            "help_center" -> Icons.Default.HelpOutline
            "settings" -> Icons.Default.Settings
            else -> Icons.Default.Circle
        }
