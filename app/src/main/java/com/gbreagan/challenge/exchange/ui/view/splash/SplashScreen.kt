package com.gbreagan.challenge.exchange.ui.view.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
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
import com.gbreagan.challenge.exchange.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun SplashScreen(onSplashFinished: () -> Unit) {
    val viewModel: SplashViewModel = koinViewModel()
    val uiState by viewModel.uiState//.collectAsStateWithLifecycle()

    Scaffold {
        Column(modifier = Modifier.padding(it)) {
            when (uiState) {
                is SplashUiState.Loading -> {
                    LaunchedEffect(Unit) { viewModel.loadData() }
                    LogoAnimation()
                }

                is SplashUiState.Error -> {
                    LaunchedEffect(Unit) {
                        onSplashFinished()
                    }
                }

                is SplashUiState.Success -> {
                    LaunchedEffect(Unit) {
                        onSplashFinished()
                    }
                }
            }
        }
    }
}

@Composable
private fun LogoAnimation() {
    val scale = remember { Animatable(1f) }
    LaunchedEffect(Unit) {
        scale.animateTo(
            targetValue = 1.2f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 800, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            )
        )
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.illustration_moneybag),
            contentDescription = stringResource(id = R.string.app_name),
            modifier = Modifier
                .size(128.dp)
                .graphicsLayer(
                    scaleX = scale.value,
                    scaleY = scale.value
                )
        )
    }
}