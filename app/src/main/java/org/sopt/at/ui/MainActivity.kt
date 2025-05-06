package org.sopt.at.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import org.sopt.at.navigation.MainNavHost
import org.sopt.at.navigation.MainNavRoute
import org.sopt.at.navigation.MainNavRoute.Home
import org.sopt.at.navigation.MainNavRoute.SignIn
import org.sopt.at.navigation.MainNavigation
import org.sopt.at.ui.theme.TvingTheme
import org.sopt.at.utils.SharedPreferencesManager

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            SharedPreferencesManager.init(context = this)
            val isLoggedIn = SharedPreferencesManager.isLoggedIn()
            val startDestination =
                if (isLoggedIn) Home.route else SignIn.route

            val navController = rememberNavController()

            TvingTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black),
                    bottomBar = {
                            MainNavigation(
                                navController = navController,
                            )
                    }
                ) { innerPadding ->
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
