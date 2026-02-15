package com.lalamove.clone.feature.inbox.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.lalamove.clone.theme.*
import kotlinx.coroutines.launch

data class PromoItem(
        val title: String,
        val description: String,
        val date: String,
        val gradientStart: Color,
        val gradientEnd: Color
)

data class NotificationItem(
        val title: String,
        val message: String,
        val time: String,
        val isRead: Boolean
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InboxScreen(onBack: () -> Unit, modifier: Modifier = Modifier) {
        val pagerState = rememberPagerState(initialPage = 1, pageCount = { 2 })
        val scope = rememberCoroutineScope()

        val sampleNotifications =
                listOf(
                        NotificationItem(
                                "Order Completed",
                                "Your delivery #LAL-2026-4521 has been completed successfully.",
                                "2 hours ago",
                                false
                        ),
                        NotificationItem(
                                "Driver Assigned",
                                "A driver has been assigned to your order #LAL-2026-4520.",
                                "5 hours ago",
                                true
                        ),
                        NotificationItem(
                                "Payment Received",
                                "We've received your payment of S\$12.50.",
                                "1 day ago",
                                true
                        )
                )

        val samplePromos =
                listOf(
                        PromoItem(
                                "Use <LALAFEB> for 25% Off Deliveries & Win Lucky Draw Prizes! T&Cs apply.",
                                "Get discounts on your next delivery",
                                "Jan 31, 2026",
                                Color(0xFFD4534E),
                                Color(0xFFF5A623)
                        ),
                        PromoItem(
                                "Earn 100 LalaPoints for every delivery & win lucky prizes for Valentine's Day!",
                                "Sign up for Lalamove Rewards today",
                                "Jan 31, 2026",
                                Color(0xFFE91E63),
                                Color(0xFFFF9800)
                        ),
                        PromoItem(
                                "Join Lalamove Rewards & Get Up To 1,000 LalaPoints!",
                                "New member bonus available now",
                                "Feb 01, 2026",
                                Color(0xFFFF6F00),
                                Color(0xFFFFB74D)
                        )
                )

        Scaffold(
                topBar = {
                        CenterAlignedTopAppBar(
                                title = {
                                        Text(
                                                text = "Inbox",
                                                style = MaterialTheme.typography.titleLarge,
                                                fontWeight = FontWeight.SemiBold
                                        )
                                },
                                navigationIcon = {
                                        IconButton(onClick = onBack) {
                                                Icon(
                                                        Icons.Default.ArrowBack,
                                                        contentDescription = "Back"
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
                Column(modifier = modifier.fillMaxSize().padding(padding)) {
                        // Tab Row
                        TabRow(
                                selectedTabIndex = pagerState.currentPage,
                                containerColor = NeutralWhite,
                                contentColor = NeutralGray900
                        ) {
                                Tab(
                                        selected = pagerState.currentPage == 0,
                                        onClick = {
                                                scope.launch { pagerState.animateScrollToPage(0) }
                                        },
                                        text = {
                                                Text(
                                                        "Notifications",
                                                        fontWeight =
                                                                if (pagerState.currentPage == 0)
                                                                        FontWeight.SemiBold
                                                                else FontWeight.Normal
                                                )
                                        },
                                        selectedContentColor = NeutralGray900,
                                        unselectedContentColor = NeutralGray600
                                )
                                Tab(
                                        selected = pagerState.currentPage == 1,
                                        onClick = {
                                                scope.launch { pagerState.animateScrollToPage(1) }
                                        },
                                        text = {
                                                Text(
                                                        "Promotions",
                                                        fontWeight =
                                                                if (pagerState.currentPage == 1)
                                                                        FontWeight.SemiBold
                                                                else FontWeight.Normal
                                                )
                                        },
                                        selectedContentColor = NeutralGray900,
                                        unselectedContentColor = NeutralGray600
                                )
                        }

                        HorizontalPager(state = pagerState, modifier = Modifier.fillMaxSize()) {
                                page ->
                                when (page) {
                                        0 -> NotificationsTab(notifications = sampleNotifications)
                                        1 -> PromotionsTab(promos = samplePromos)
                                }
                        }
                }
        }
}

@Composable
private fun NotificationsTab(notifications: List<NotificationItem>) {
        Column(
                modifier =
                        Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp)
        ) {
                notifications.forEach { notification ->
                        Card(
                                modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
                                shape = RoundedCornerShape(12.dp),
                                colors =
                                        CardDefaults.cardColors(
                                                containerColor =
                                                        if (notification.isRead) NeutralWhite
                                                        else LalamoveOrangeSurface
                                        )
                        ) {
                                Row(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                                        if (!notification.isRead) {
                                                Box(
                                                        modifier =
                                                                Modifier.padding(
                                                                                top = 6.dp,
                                                                                end = 12.dp
                                                                        )
                                                                        .size(8.dp)
                                                                        .clip(CircleShape)
                                                                        .background(LalamoveOrange)
                                                )
                                        }
                                        Column(modifier = Modifier.weight(1f)) {
                                                Text(
                                                        text = notification.title,
                                                        style = MaterialTheme.typography.titleSmall,
                                                        fontWeight = FontWeight.SemiBold,
                                                        color = NeutralGray900
                                                )
                                                Spacer(Modifier.height(4.dp))
                                                Text(
                                                        text = notification.message,
                                                        style = MaterialTheme.typography.bodySmall,
                                                        color = NeutralGray600
                                                )
                                                Spacer(Modifier.height(6.dp))
                                                Text(
                                                        text = notification.time,
                                                        style = MaterialTheme.typography.labelSmall,
                                                        color = NeutralGray400
                                                )
                                        }
                                }
                        }
                }
        }
}

@Composable
private fun PromotionsTab(promos: List<PromoItem>) {
        Column(
                modifier =
                        Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp)
        ) {
                promos.forEach { promo ->
                        Card(
                                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                                shape = RoundedCornerShape(16.dp),
                                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                                colors = CardDefaults.cardColors(containerColor = NeutralWhite)
                        ) {
                                Column {
                                        // Gradient promo banner
                                        Box(
                                                modifier =
                                                        Modifier.fillMaxWidth()
                                                                .height(140.dp)
                                                                .clip(
                                                                        RoundedCornerShape(
                                                                                topStart = 16.dp,
                                                                                topEnd = 16.dp
                                                                        )
                                                                )
                                                                .background(
                                                                        brush =
                                                                                Brush.horizontalGradient(
                                                                                        colors =
                                                                                                listOf(
                                                                                                        promo.gradientStart,
                                                                                                        promo.gradientEnd
                                                                                                )
                                                                                )
                                                                ),
                                                contentAlignment = Alignment.Center
                                        ) {
                                                // Decorative elements
                                                Canvas(modifier = Modifier.fillMaxSize()) {
                                                        drawCircle(
                                                                color =
                                                                        Color.White.copy(
                                                                                alpha = 0.1f
                                                                        ),
                                                                radius = size.minDimension * 0.4f,
                                                                center =
                                                                        androidx.compose.ui.geometry
                                                                                .Offset(
                                                                                        size.width *
                                                                                                0.8f,
                                                                                        size.height *
                                                                                                0.3f
                                                                                )
                                                        )
                                                        drawRoundRect(
                                                                color =
                                                                        Color.White.copy(
                                                                                alpha = 0.15f
                                                                        ),
                                                                topLeft =
                                                                        androidx.compose.ui.geometry
                                                                                .Offset(
                                                                                        size.width *
                                                                                                0.1f,
                                                                                        size.height *
                                                                                                0.2f
                                                                                ),
                                                                size =
                                                                        androidx.compose.ui.geometry
                                                                                .Size(
                                                                                        size.width *
                                                                                                0.3f,
                                                                                        size.height *
                                                                                                0.5f
                                                                                ),
                                                                cornerRadius = CornerRadius(12f)
                                                        )
                                                }

                                                Text(
                                                        text = "LALAMOVE",
                                                        style =
                                                                MaterialTheme.typography
                                                                        .headlineMedium,
                                                        fontWeight = FontWeight.Bold,
                                                        color = Color.White.copy(alpha = 0.3f)
                                                )
                                        }

                                        // Content
                                        Column(modifier = Modifier.padding(16.dp)) {
                                                Row(
                                                        verticalAlignment =
                                                                Alignment.CenterVertically
                                                ) {
                                                        Box(
                                                                modifier =
                                                                        Modifier.size(8.dp)
                                                                                .clip(CircleShape)
                                                                                .background(
                                                                                        LalamoveOrange
                                                                                )
                                                        )
                                                        Spacer(Modifier.width(8.dp))
                                                        Text(
                                                                text = promo.title,
                                                                style =
                                                                        MaterialTheme.typography
                                                                                .bodyMedium,
                                                                fontWeight = FontWeight.Medium,
                                                                color = NeutralGray900
                                                        )
                                                }
                                                Spacer(Modifier.height(8.dp))
                                                Text(
                                                        text = promo.date,
                                                        style = MaterialTheme.typography.labelSmall,
                                                        color = NeutralGray400
                                                )
                                        }
                                }
                        }
                }
        }
}
