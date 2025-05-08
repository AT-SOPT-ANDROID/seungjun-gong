package org.sopt.at.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.sopt.at.ui.history.HistoryScreen
import org.sopt.at.ui.home.HomeScreen
import org.sopt.at.ui.live.LiveScreen
import org.sopt.at.ui.login.signin.SignInScreen
import org.sopt.at.ui.login.signup.SignUpScreen
import org.sopt.at.ui.my.MyScreen
import org.sopt.at.ui.search.SearchScreen
import org.sopt.at.ui.shorts.ShortsScreen


@Composable
fun MainNavHost(
    navController: NavHostController,
    paddingValues: PaddingValues,
    startDestination: Route,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = Modifier.padding(paddingValues),
    ) {
        composable<MainBottomNavRoute.Home> {
            HomeScreen(
                navigateToMyScreen = { navController.navigateAddTo(Route.My) }
            )
        }
        composable<MainBottomNavRoute.Shorts> { ShortsScreen() }
        composable<MainBottomNavRoute.Live> { LiveScreen() }
        composable<MainBottomNavRoute.Search> { SearchScreen() }
        composable<MainBottomNavRoute.History> { HistoryScreen() }
        composable<Route.My> { MyScreen() }
        composable<Route.SignIn> {
            SignInScreen(
                navigateToHomeScreen = { navController.navigateReplaceTo(route = MainBottomNavRoute.Home) },
                navigateToSignUpScreen = { navController.navigateAddTo(Route.SignUp) },
                navController = navController,
            )
        }
        composable<Route.SignUp> {
            SignUpScreen(
                navigateToSignInScreen = {
                    navController.navigateReplaceTo(Route.SignIn)
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
    route: Route,
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
    route: Route,
) {
    val currentRoute = this.currentBackStackEntry?.destination?.route ?: return
    this.navigate(route) {
        popUpTo(currentRoute) {
            inclusive = true
        }
        launchSingleTop = true
    }
}