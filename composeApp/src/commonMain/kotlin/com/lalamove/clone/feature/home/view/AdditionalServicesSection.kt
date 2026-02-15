package com.lalamove.clone.feature.home.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.lalamove.clone.feature.home.entity.AdditionalService
import com.lalamove.clone.theme.*

@Composable
fun AdditionalServicesSection(services: List<AdditionalService>, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(horizontal = 16.dp)) {
        Text(
                text = "Additional services",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = NeutralGray900,
                modifier = Modifier.padding(bottom = 12.dp)
        )

        services.forEach { service ->
            AdditionalServiceCard(service = service)
            Spacer(Modifier.height(8.dp))
        }
    }
}

@Composable
private fun AdditionalServiceCard(service: AdditionalService) {
    Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = NeutralGray100),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                    text = service.name,
                    style = MaterialTheme.typography.bodyMedium,
                    color = NeutralGray800,
                    modifier = Modifier.weight(1f)
            )
            Spacer(Modifier.width(16.dp))
            Text(
                    text = service.price,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = NeutralGray900
            )
        }
    }
}
