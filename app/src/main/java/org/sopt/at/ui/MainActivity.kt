package org.sopt.at.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import org.sopt.at.navigation.MainBottomNavRoute
import org.sopt.at.navigation.MainNavHost
import org.sopt.at.navigation.MainNavigation
import org.sopt.at.navigation.Route
import org.sopt.at.ui.theme.TvingTheme
import org.sopt.at.utils.SharedPreferencesManager

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            SharedPreferencesManager.init(context = this)
            val isLoggedIn = SharedPreferencesManager.isLoggedIn()
            val startDestination = if (isLoggedIn) MainBottomNavRoute.Home else Route.SignIn

            val navController = rememberNavController()

            TvingTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black), bottomBar = {
                        MainNavigation(
                            navController = navController,
                        )
                    }) { innerPadding ->
                    MainNavHost(
                        navController = navController,
                        paddingValues = innerPadding,
                        startDestination = startDestination,
                    )
                }

            }
        }
    }
}
