package com.lalamove.clone.navigation

import kotlinx.serialization.Serializable

/**
 * Navigation 3 NavKey definitions for all app destinations. Each implements @Serializable for
 * type-safe routing.
 */
sealed interface AppRoute {
    @Serializable data object Home : AppRoute

    @Serializable data object Orders : AppRoute

    @Serializable data object Wallet : AppRoute

    @Serializable data object Inbox : AppRoute

    @Serializable data object Settings : AppRoute

    @Serializable data object DeliveryForm : AppRoute

    @Serializable data object MyDrivers : AppRoute

    @Serializable data object Rewards : AppRoute

    @Serializable data object CouponShop : AppRoute

    @Serializable data object HelpCenter : AppRoute
}
