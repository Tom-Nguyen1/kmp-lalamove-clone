package com.lalamove.clone.feature.wallet.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.lalamove.clone.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WalletScreen(onBack: () -> Unit, modifier: Modifier = Modifier) {
    Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                        title = {
                            Text(
                                    text = "Wallet",
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.SemiBold
                            )
                        },
                        navigationIcon = {
                            IconButton(onClick = onBack) {
                                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                            }
                        },
                        actions = {
                            TextButton(onClick = {}) {
                                Text(
                                        text = "History",
                                        color = LalamoveOrange,
                                        fontWeight = FontWeight.SemiBold
                                )
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
        Column(modifier = modifier.fillMaxSize().padding(padding).padding(16.dp)) {
            // Balance Card
            Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = NeutralWhite),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.fillMaxWidth().padding(24.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                                Icons.Default.AccountBalanceWallet,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp),
                                tint = NeutralGray600
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(
                                text = "Balance",
                                style = MaterialTheme.typography.bodyMedium,
                                color = NeutralGray600
                        )
                    }

                    Spacer(Modifier.height(8.dp))

                    Text(
                            text = "S\$0.00",
                            style = MaterialTheme.typography.displayMedium,
                            fontWeight = FontWeight.Bold,
                            color = NeutralGray900
                    )

                    Spacer(Modifier.height(20.dp))

                    Button(
                            onClick = {},
                            modifier = Modifier.wrapContentWidth(),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = LalamoveOrange),
                            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp)
                    ) {
                        Icon(
                                Icons.Default.Add,
                                contentDescription = null,
                                modifier = Modifier.size(20.dp)
                        )
                        Spacer(Modifier.width(6.dp))
                        Text(text = "Top Up", fontWeight = FontWeight.SemiBold)
                    }
                }
            }

            Spacer(Modifier.height(24.dp))

            // Payment Methods
            WalletOptionRow(
                    icon = Icons.Default.CreditCard,
                    iconTint = LalamoveOrange,
                    title = "Payment Methods",
                    onClick = {}
            )

            HorizontalDivider(color = NeutralGray200)

            // Coupons
            WalletOptionRow(
                    icon = Icons.Default.LocalOffer,
                    iconTint = TealAccent,
                    title = "Coupons",
                    onClick = {}
            )
        }
    }
}

@Composable
private fun WalletOptionRow(
        icon: ImageVector,
        iconTint: Color,
        title: String,
        onClick: () -> Unit
) {
    Row(
            modifier =
                    Modifier.fillMaxWidth()
                            .clickable { onClick() }
                            .padding(vertical = 18.dp, horizontal = 4.dp),
            verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = title, modifier = Modifier.size(24.dp), tint = iconTint)
        Spacer(Modifier.width(16.dp))
        Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                color = NeutralGray900,
                modifier = Modifier.weight(1f)
        )
        Icon(
                Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = NeutralGray400
        )
    }
}
