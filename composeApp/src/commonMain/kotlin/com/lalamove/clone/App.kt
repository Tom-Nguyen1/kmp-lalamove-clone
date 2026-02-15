package com.lalamove.clone

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.lalamove.clone.feature.drawer.view.DrawerContent
import com.lalamove.clone.feature.home.view.HomeScreen
import com.lalamove.clone.feature.inbox.view.InboxScreen
import com.lalamove.clone.feature.orders.view.OrdersScreen
import com.lalamove.clone.feature.settings.view.SettingsScreen
import com.lalamove.clone.feature.wallet.view.WalletScreen
import com.lalamove.clone.navigation.AppRoute
import com.lalamove.clone.theme.LalamoveTheme

@Composable
fun App() {
        LalamoveTheme {
                var currentRoute by remember { mutableStateOf<AppRoute>(AppRoute.Home) }
                var isDrawerOpen by remember { mutableStateOf(false) }

                // Simple backstack for navigation
                val backStack = remember { mutableStateListOf<AppRoute>() }

                val navigateTo: (AppRoute) -> Unit = { route ->
                        backStack.add(currentRoute)
                        currentRoute = route
                        isDrawerOpen = false
                }

                val navigateBack: () -> Unit = {
                        if (backStack.isNotEmpty()) {
                                currentRoute = backStack.removeLast()
                        }
                }

                Box(modifier = Modifier.fillMaxSize()) {
                        // Main content
                        AnimatedContent(
                                targetState = currentRoute,
                                transitionSpec = {
                                        val enter =
                                                slideInHorizontally(
                                                        initialOffsetX = { it },
                                                        animationSpec = tween(300)
                                                ) + fadeIn(tween(200))
                                        val exit =
                                                slideOutHorizontally(
                                                        targetOffsetX = { -it / 3 },
                                                        animationSpec = tween(300)
                                                ) + fadeOut(tween(200))
                                        enter togetherWith exit
                                },
                                modifier = Modifier.fillMaxSize()
                        ) { route ->
                                when (route) {
                                        AppRoute.Home ->
                                                HomeScreen(
                                                        onMenuClick = { isDrawerOpen = true },
                                                        onNotificationsClick = {
                                                                navigateTo(AppRoute.Inbox)
                                                        }
                                                )
                                        AppRoute.Orders -> OrdersScreen(onBack = navigateBack)
                                        AppRoute.Wallet -> WalletScreen(onBack = navigateBack)
                                        AppRoute.Inbox -> InboxScreen(onBack = navigateBack)
                                        AppRoute.Settings -> SettingsScreen(onBack = navigateBack)
                                        AppRoute.DeliveryForm ->
                                                PlaceholderScreen("Delivery Form", navigateBack)
                                        AppRoute.MyDrivers ->
                                                PlaceholderScreen("My Drivers", navigateBack)
                                        AppRoute.Rewards ->
                                                PlaceholderScreen("Rewards", navigateBack)
                                        AppRoute.CouponShop ->
                                                PlaceholderScreen("Coupon Shop", navigateBack)
                                        AppRoute.HelpCenter ->
                                                PlaceholderScreen("Help Center", navigateBack)
                                }
                        }

                        // Drawer overlay
                        AnimatedVisibility(
                                visible = isDrawerOpen,
                                enter = fadeIn(tween(200)),
                                exit = fadeOut(tween(200))
                        ) {
                                Box(
                                        modifier =
                                                Modifier.fillMaxSize()
                                                        .background(Color.Black.copy(alpha = 0.4f))
                                                        .clickable(
                                                                interactionSource =
                                                                        remember {
                                                                                MutableInteractionSource()
                                                                        },
                                                                indication = null
                                                        ) { isDrawerOpen = false }
                                )
                        }

                        // Drawer content
                        AnimatedVisibility(
                                visible = isDrawerOpen,
                                enter =
                                        slideInHorizontally(
                                                initialOffsetX = { -it },
                                                animationSpec = tween(300)
                                        ),
                                exit =
                                        slideOutHorizontally(
                                                targetOffsetX = { -it },
                                                animationSpec = tween(300)
                                        )
                        ) {
                                DrawerContent(
                                        onItemClick = { itemId ->
                                                val route =
                                                        when (itemId) {
                                                                "orders" -> AppRoute.Orders
                                                                "wallet" -> AppRoute.Wallet
                                                                "delivery_form" ->
                                                                        AppRoute.DeliveryForm
                                                                "my_drivers" -> AppRoute.MyDrivers
                                                                "rewards" -> AppRoute.Rewards
                                                                "coupon_shop" -> AppRoute.CouponShop
                                                                "help_center" -> AppRoute.HelpCenter
                                                                "settings" -> AppRoute.Settings
                                                                else -> return@DrawerContent
                                                        }
                                                navigateTo(route)
                                        },
                                        onClose = { isDrawerOpen = false }
                                )
                        }
                }
        }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PlaceholderScreen(title: String, onBack: () -> Unit) {
        Scaffold(
                topBar = {
                        CenterAlignedTopAppBar(
                                title = {
                                        Text(
                                                text = title,
                                                style = MaterialTheme.typography.titleLarge
                                        )
                                },
                                navigationIcon = {
                                        IconButton(onClick = onBack) {
                                                Icon(
                                                        Icons.Default.ArrowBack,
                                                        contentDescription = "Back"
                                                )
                                        }
                                }
                        )
                }
        ) { padding ->
                Box(
                        modifier = Modifier.fillMaxSize().padding(padding),
                        contentAlignment = Alignment.Center
                ) {
                        Text(
                                text = "$title - Coming Soon",
                                style = MaterialTheme.typography.headlineSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                }
        }
}
