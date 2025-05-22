package com.gbreagan.challenge.exchange

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import com.gbreagan.challenge.exchange.ui.navigation.Navigation
import com.gbreagan.challenge.exchange.ui.theme.ExchangeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExchangeTheme(dynamicColor = false) {
                Surface {
                    val navController = rememberNavController()
                    Navigation(navController = navController)
                }
            }
        }
    }
}