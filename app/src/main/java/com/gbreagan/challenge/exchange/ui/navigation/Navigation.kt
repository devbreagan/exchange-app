package com.gbreagan.challenge.exchange.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.gbreagan.challenge.exchange.ui.view.home.HomeScreen
import com.gbreagan.challenge.exchange.ui.view.splash.SplashScreen
import kotlinx.serialization.Serializable

@Composable
fun Navigation(
    navController: NavHostController
) {
    NavHost(navController, startDestination = Screen.Splash) {
        composable<Screen.Splash> {
            SplashScreen {
                navController.navigate(Screen.Home) {
                    popUpTo(Screen.Splash) { inclusive = true }
                }
            }
        }
        composable<Screen.Home> {
            HomeScreen()
        }
    }
}

@Serializable
sealed interface Screen {
    @Serializable data object Splash : Screen
    @Serializable data object Home : Screen
}