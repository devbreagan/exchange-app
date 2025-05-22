package com.gbreagan.challenge.exchange.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.gbreagan.challenge.exchange.ui.view.detail.DetailScreen
import com.gbreagan.challenge.exchange.ui.view.home.HomeScreen
import com.gbreagan.challenge.exchange.ui.view.option.OptionScreen
import com.gbreagan.challenge.exchange.ui.view.splash.SplashScreen
import kotlinx.serialization.Serializable

@Composable
fun Navigation(
    navController: NavHostController
) {
    val selectedOptions = remember { mutableStateMapOf<String, String>() }

    NavHost(navController, startDestination = Screen.Splash) {
        composable<Screen.Splash> {
            SplashScreen(onSplashFinished = {
                navController.navigate(Screen.Home) {
                    popUpTo(Screen.Splash) { inclusive = true }
                }
            })
        }
        composable<Screen.Home> { entry ->
            val navEntry = remember { navController.currentBackStackEntry }
            val savedStateHandle = navEntry?.savedStateHandle

            LaunchedEffect(Unit) {
                savedStateHandle?.getLiveData<Pair<String, String>>(NavConstants.SELECTED_OPTION)
                    ?.observeForever { (source, selectedItem) ->
                        selectedOptions[source] = selectedItem
                        savedStateHandle.remove<Pair<String, String>>(NavConstants.SELECTED_OPTION)
                    }
            }

            HomeScreen(
                selectedOptions = selectedOptions,
                onSelectCurrency = {
                    navController.navigate(Screen.Option(it))
                },
                onDetailScreen = {
                    navController.navigate(Screen.Detail)
                }
            )
        }
        composable<Screen.Option> { entry ->
            val arguments = entry.toRoute<Screen.Option>()
            OptionScreen(source = arguments.source, onItemSelected = { source, value ->
                navController.previousBackStackEntry?.savedStateHandle?.set(
                    NavConstants.SELECTED_OPTION,
                    Pair(source, value)
                )
                navController.popBackStack()
            }, onBackPressed = {
                navController.popBackStack()
            })
        }
        composable<Screen.Detail> {
            DetailScreen(
                onBackPressed = {
                    navController.popBackStack()
                }
            )
        }
    }
}

object NavConstants {
    const val SELECTED_OPTION = "selected_option"
}

@Serializable
sealed interface Screen {
    @Serializable
    data object Splash : Screen

    @Serializable
    data object Home : Screen

    @Serializable
    data class Option(val source: String) : Screen

    @Serializable
    data object Detail : Screen
}