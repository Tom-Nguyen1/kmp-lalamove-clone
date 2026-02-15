package com.lalamove.clone.feature.drawer.entity

data class DrawerMenuItem(val id: String, val title: String, val iconName: String)

data class DrawerUiState(
        val userName: String = "Welcome",
        val region: String = "Singapore",
        val menuItems: List<DrawerMenuItem> =
                listOf(
                        DrawerMenuItem("orders", "Orders", "orders"),
                        DrawerMenuItem("wallet", "Wallet", "wallet"),
                        DrawerMenuItem("delivery_form", "Delivery Form", "delivery_form"),
                        DrawerMenuItem("my_drivers", "My Drivers", "my_drivers"),
                        DrawerMenuItem("rewards", "Rewards", "rewards"),
                        DrawerMenuItem("coupon_shop", "Coupon Shop", "coupon_shop"),
                        DrawerMenuItem("help_center", "Help Center", "help_center"),
                        DrawerMenuItem("settings", "Settings", "settings")
                )
)
