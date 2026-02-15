package com.lalamove.clone.feature.home.view

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.lalamove.clone.feature.home.entity.PromoBanner
import com.lalamove.clone.theme.*
import kotlinx.coroutines.delay

@Composable
fun PromoCarousel(banners: List<PromoBanner>, modifier: Modifier = Modifier) {
    if (banners.isEmpty()) return

    val pagerState = rememberPagerState(pageCount = { banners.size })

    // Auto-scroll
    LaunchedEffect(pagerState) {
        while (true) {
            delay(4000)
            val next = (pagerState.currentPage + 1) % banners.size
            pagerState.animateScrollToPage(next, animationSpec = tween(600))
        }
    }

    Column(modifier = modifier) {
        HorizontalPager(
                state = pagerState,
                contentPadding = PaddingValues(horizontal = 16.dp),
                pageSpacing = 12.dp,
                modifier = Modifier.fillMaxWidth()
        ) { page -> PromoBannerCard(banner = banners[page]) }

        Spacer(Modifier.height(12.dp))

        // Page indicators
        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
            repeat(banners.size) { index ->
                val isSelected = pagerState.currentPage == index
                val width by
                        animateDpAsState(
                                targetValue = if (isSelected) 20.dp else 6.dp,
                                animationSpec = tween(300)
                        )
                val color by
                        animateColorAsState(
                                targetValue = if (isSelected) LalamoveOrange else NeutralGray400,
                                animationSpec = tween(300)
                        )
                Box(
                        modifier =
                                Modifier.padding(horizontal = 3.dp)
                                        .width(width)
                                        .height(6.dp)
                                        .clip(CircleShape)
                                        .background(color)
                )
            }
        }
    }
}

@Composable
private fun PromoBannerCard(banner: PromoBanner, modifier: Modifier = Modifier) {
    Box(
            modifier =
                    modifier.fillMaxWidth()
                            .height(180.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(
                                    brush =
                                            Brush.horizontalGradient(
                                                    colors =
                                                            listOf(
                                                                    Color(banner.backgroundColor),
                                                                    GradientPromoEnd
                                                            )
                                            )
                            )
                            .padding(20.dp)
    ) {
        Column(
                modifier = Modifier.fillMaxHeight().fillMaxWidth(0.65f),
                verticalArrangement = Arrangement.Center
        ) {
            Text(
                    text = banner.title,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(8.dp))
            Text(
                    text = banner.subtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.9f)
            )
        }

        // Decorative circles on the right
        Canvas(modifier = Modifier.align(Alignment.CenterEnd).size(120.dp)) {
            drawCircle(color = Color.White.copy(alpha = 0.15f), radius = size.minDimension / 2)
            drawCircle(color = Color.White.copy(alpha = 0.1f), radius = size.minDimension / 3)
            // Star decoration
            drawRoundRect(
                    color = Color.White.copy(alpha = 0.2f),
                    cornerRadius = CornerRadius(8f, 8f),
                    size = androidx.compose.ui.geometry.Size(40f, 40f),
                    topLeft =
                            androidx.compose.ui.geometry.Offset(
                                    (size.width - 40f) / 2,
                                    (size.height - 40f) / 2
                            )
            )
        }
    }
}
