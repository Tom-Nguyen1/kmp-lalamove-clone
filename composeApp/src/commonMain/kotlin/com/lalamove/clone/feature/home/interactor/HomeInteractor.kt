package com.lalamove.clone.feature.home.interactor

import com.lalamove.clone.feature.home.entity.*

/**
 * VIPER Interactor — encapsulates business logic for the Home module. In a real app, this would
 * fetch from API/repository.
 */
class HomeInteractor {

    fun loadPromoBanners(): List<PromoBanner> =
            listOf(
                    PromoBanner(
                            id = "1",
                            title = "Win Up to \$1500 Worth of Prizes!",
                            subtitle = "Complete missions and stand a chance to win today."
                    ),
                    PromoBanner(
                            id = "2",
                            title = "25% Off Deliveries",
                            subtitle = "Use code LALAFEB for discounts on all orders."
                    ),
                    PromoBanner(
                            id = "3",
                            title = "Earn 100 LalaPoints",
                            subtitle = "For every delivery, earn rewards and redeem prizes."
                    ),
                    PromoBanner(
                            id = "4",
                            title = "Refer & Earn S\$15",
                            subtitle = "Share your referral code with friends and earn credits."
                    )
            )

    fun loadVehicles(): List<Vehicle> =
            listOf(
                    Vehicle(
                            id = "courier",
                            name = "Courier",
                            description =
                                    "Perfect for small goods, with a faster order pickup time",
                            dimensions = "0.4 x 0.3 x 0.3 Meter",
                            maxWeight = "Up to 8 kg",
                            iconName = "courier"
                    ),
                    Vehicle(
                            id = "car",
                            name = "Car",
                            description = "Ideal for medium-sized parcels and documents",
                            dimensions = "0.5 x 0.5 x 0.5 Meter",
                            maxWeight = "Up to 20 kg",
                            iconName = "car"
                    ),
                    Vehicle(
                            id = "mpv",
                            name = "MPV (Weight<25KG x 2)",
                            description = "Great for bulky items and multiple parcels",
                            dimensions = "1.0 x 1.0 x 1.0 Meter",
                            maxWeight = "Up to 50 kg",
                            iconName = "mpv"
                    ),
                    Vehicle(
                            id = "van",
                            name = "Van",
                            description = "Best for large items and furniture",
                            dimensions = "2.0 x 1.2 x 1.2 Meter",
                            maxWeight = "Up to 200 kg",
                            iconName = "van"
                    ),
                    Vehicle(
                            id = "lorry",
                            name = "10ft Lorry",
                            description = "For heavy-duty and commercial deliveries",
                            dimensions = "3.0 x 1.5 x 1.5 Meter",
                            maxWeight = "Up to 1000 kg",
                            iconName = "lorry"
                    )
            )

    fun loadAdditionalServices(): List<AdditionalService> =
            listOf(
                    AdditionalService(
                            "1",
                            "Secure zone (extra delivery time required)",
                            "S\$15.00"
                    ),
                    AdditionalService("2", "Buy for me", "S\$10.00-15.00"),
                    AdditionalService("3", "Priority delivery", "S\$5.00"),
                    AdditionalService("4", "Help me carry", "S\$8.00")
            )
}
