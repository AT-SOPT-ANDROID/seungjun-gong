package org.sopt.at.presentation.main.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.sopt.at.presentation.main.screen.HistoryScreen
import org.sopt.at.presentation.main.screen.LiveScreen
import org.sopt.at.presentation.main.screen.MyScreen
import org.sopt.at.presentation.main.screen.SearchScreen
import org.sopt.at.presentation.main.screen.ShortsScreen
import org.sopt.at.presentation.main.screen.home.HomeScreen


@Composable
fun MainNavigationGraph(
    navController: NavHostController,
    paddingValues: PaddingValues,
) {
    NavHost(
        navController = navController,
        startDestination = Home,
    ) {
        composable<Home> {
            HomeScreen(
                paddingValues = paddingValues,
                navigateToMyScreen = {
                    navController.navigate(My) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
        composable<Shorts> { ShortsScreen(paddingValues) }
        composable<Live> { LiveScreen(paddingValues) }
        composable<Search> { SearchScreen(paddingValues) }
        composable<History> { HistoryScreen(paddingValues) }
        composable<My> { MyScreen(paddingValues) }
    }
}