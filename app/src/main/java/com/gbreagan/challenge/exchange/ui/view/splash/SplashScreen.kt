package com.gbreagan.challenge.exchange.ui.view.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gbreagan.challenge.exchange.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun SplashScreen(onSplashFinished: () -> Unit) {
    val viewModel: SplashViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) { viewModel.loadData() }

    Scaffold {
        Column(modifier = Modifier.padding(it)) {
            when (uiState) {
                is SplashUiState.Loading -> {
                    LogoAnimation(onSplashFinished)
                }
                is SplashUiState.Error -> {
                    LogoAnimation(onSplashFinished)
                }
                is SplashUiState.Success -> {

                }
            }
        }
    }
}

@Composable
private fun LogoAnimation(
    onSplashFinished: () -> Unit
) {
    val scale = remember { Animatable(0.65f) }
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
            contentDescription = stringResource(id = R.string.app_name),
            modifier = Modifier
                .size(200.dp)
                .graphicsLayer(
                    scaleX = scale.value,
                    scaleY = scale.value
                )
        )
    }
}