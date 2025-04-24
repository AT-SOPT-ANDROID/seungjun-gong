package org.sopt.at.presentation.main

import android.content.Intent
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
import org.sopt.at.presentation.login.SignInActivity
import org.sopt.at.presentation.main.navigation.MainNavigation
import org.sopt.at.presentation.main.navigation.MainNavigationGraph
import org.sopt.at.ui.theme.TvingTheme
import org.sopt.at.utils.SharedPreferencesManager

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            SharedPreferencesManager.init(context = this)
            if (!SharedPreferencesManager.isLoggedIn()) { // 로그인 체크
                val intent = Intent(this, SignInActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                }
                this.startActivity(intent)
            }

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
                    MainNavigationGraph(
                        navController = navController,
                        paddingValues = innerPadding,
                    )
                }

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MainActivityPreview() {
    TvingTheme {
    }
}