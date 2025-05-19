package com.gbreagan.challenge.exchange.ui.view.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.gbreagan.challenge.exchange.R

@Composable
fun SplashScreen(onSplashFinished: () -> Unit) {
    val scale = remember { Animatable(0.7f) }

    LaunchedEffect(Unit) {
        scale.animateTo(
            targetValue = 1.5f,
            animationSpec = tween(
                durationMillis = SplashConstants.SPLASH_DURATION_MS,
                easing = LinearEasing
            )
        )
        onSplashFinished()
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.illustration_moneybag),
            contentDescription = "Logo",
            modifier = Modifier
                .size(200.dp)
                .graphicsLayer(
                    scaleX = scale.value,
                    scaleY = scale.value
                )
        )
    }
}