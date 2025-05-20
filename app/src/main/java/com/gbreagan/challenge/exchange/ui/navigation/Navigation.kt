package com.gbreagan.challenge.exchange.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.gbreagan.challenge.exchange.ui.view.home.HomeScreen
import com.gbreagan.challenge.exchange.ui.view.option.OptionScreen
import com.gbreagan.challenge.exchange.ui.view.splash.SplashScreen
import kotlinx.serialization.Serializable

@Composable
fun Navigation(
    navController: NavHostController
) {
    NavHost(navController, startDestination = Screen.Splash) {
        composable<Screen.Splash> {
            SplashScreen(
                onSplashFinished = {
                    navController.navigate(Screen.Home("","")) {
                        popUpTo(Screen.Splash) { inclusive = true }
                    }
                }
            )
        }
        composable<Screen.Home>() { entry ->
            val arguments = entry.toRoute<Screen.Home>()

            HomeScreen(
                onSelectCurrency = {
                    navController.navigate(Screen.Option)
                },
                sendCurrency = arguments.sendCurrency,
                receiveCurrency = arguments.receiveCurrency
            )
        }
        composable<Screen.Option> {
            OptionScreen(
                onItemSelected = { a, b ->
                    navController.navigate(Screen.Home("", "")) {
                        popUpTo(Screen.Home) { inclusive = true }
                    }
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}

@Serializable
sealed interface Screen {
    @Serializable data object Splash : Screen
    @Serializable data class Home(val sendCurrency: String, val receiveCurrency: String) : Screen
    @Serializable data object Option : Screen
}