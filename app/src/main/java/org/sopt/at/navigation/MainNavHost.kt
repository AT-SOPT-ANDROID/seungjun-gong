package org.sopt.at.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.sopt.at.navigation.MainNavRoute.History
import org.sopt.at.navigation.MainNavRoute.Home
import org.sopt.at.navigation.MainNavRoute.Live
import org.sopt.at.navigation.MainNavRoute.My
import org.sopt.at.navigation.MainNavRoute.Search
import org.sopt.at.navigation.MainNavRoute.Shorts
import org.sopt.at.navigation.MainNavRoute.SignIn
import org.sopt.at.navigation.MainNavRoute.SignUp
import org.sopt.at.ui.history.HistoryScreen
import org.sopt.at.ui.home.HomeScreen
import org.sopt.at.ui.live.LiveScreen
import org.sopt.at.ui.login.SignInScreen
import org.sopt.at.ui.login.SignUpScreen
import org.sopt.at.ui.my.MyScreen
import org.sopt.at.ui.search.SearchScreen
import org.sopt.at.ui.shorts.ShortsScreen


@Composable
fun MainNavHost(
    navController: NavHostController,
    paddingValues: PaddingValues,
    startDestination: String,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = Modifier.padding(paddingValues),
    ) {
        composable(Home.route) {
            HomeScreen(
                navigateToMyScreen = { navController.navigateAddTo(My.route) }
            )
        }
        composable(Shorts.route) { ShortsScreen() }
        composable(Live.route) { LiveScreen() }
        composable(Search.route) { SearchScreen() }
        composable(History.route) { HistoryScreen() }
        composable(My.route) { MyScreen() }
        composable(SignIn.route) { backStackEntry ->
            SignInScreen(
                navigateToHomeScreen = { navController.navigateReplaceTo(route = Home.route) },
                navigateToSignUpScreen = { navController.navigateAddTo(SignUp.route) },
                navController = navController,
            )
        }
        composable(SignUp.route) {
            SignUpScreen(
                navigateToSignInScreen = {
                    navController.navigateReplaceTo(SignIn.route)
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        "signUpSuccess",
                        true
                    )
                },
            )
        }

    }
}

fun NavHostController.navigateAddTo(
    route: String,
) {
    this.navigate(route) {
        popUpTo(route) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

fun NavHostController.navigateReplaceTo(
    route: String,
) {
    val currentRoute = this.currentBackStackEntry?.destination?.route ?: return
    this.navigate(route) {
        popUpTo(currentRoute) {
            inclusive = true
        }
        launchSingleTop = true
    }
}