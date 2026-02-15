package com.lalamove.clone.feature.settings.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.lalamove.clone.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(onBack: () -> Unit, modifier: Modifier = Modifier) {
    Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                        title = {
                            Text(
                                    text = "Settings",
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
            SettingsSection(title = "Account") {
                SettingsRow(icon = Icons.Default.Person, title = "Profile")
                SettingsRow(icon = Icons.Default.Phone, title = "Phone Number")
                SettingsRow(icon = Icons.Default.Email, title = "Email")
            }

            Spacer(Modifier.height(16.dp))

            SettingsSection(title = "Preferences") {
                SettingsRow(icon = Icons.Default.Language, title = "Language")
                SettingsRow(icon = Icons.Default.Notifications, title = "Notifications")
                SettingsRow(icon = Icons.Default.DarkMode, title = "Dark Mode")
            }

            Spacer(Modifier.height(16.dp))

            SettingsSection(title = "General") {
                SettingsRow(icon = Icons.Default.Info, title = "About")
                SettingsRow(icon = Icons.Default.PrivacyTip, title = "Privacy Policy")
                SettingsRow(icon = Icons.Default.Description, title = "Terms of Service")
            }

            Spacer(Modifier.height(24.dp))

            OutlinedButton(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = ErrorRed)
            ) {
                Icon(
                        Icons.Default.Logout,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text("Log Out", fontWeight = FontWeight.SemiBold)
            }

            Spacer(Modifier.height(16.dp))

            Text(
                    text = "Version 1.0.0",
                    style = MaterialTheme.typography.labelSmall,
                    color = NeutralGray400,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Composable
private fun SettingsSection(title: String, content: @Composable ColumnScope.() -> Unit) {
    Card(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.cardColors(containerColor = NeutralWhite)
    ) {
        Column(modifier = Modifier.padding(vertical = 8.dp)) {
            Text(
                    text = title,
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = NeutralGray400,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
            content()
        }
    }
}

@Composable
private fun SettingsRow(icon: ImageVector, title: String) {
    Row(
            modifier =
                    Modifier.fillMaxWidth()
                            .clickable {}
                            .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
                icon,
                contentDescription = title,
                modifier = Modifier.size(22.dp),
                tint = NeutralGray600
        )
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
                modifier = Modifier.size(20.dp),
                tint = NeutralGray400
        )
    }
}
