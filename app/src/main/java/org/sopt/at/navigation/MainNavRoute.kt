package org.sopt.at.navigation

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object My : Route

    @Serializable
    data object SignIn : Route

    @Serializable
    data object SignUp : Route
}

sealed interface MainBottomNavRoute : Route {
    @Serializable
    data object Home : MainBottomNavRoute

    @Serializable
    data object Shorts : MainBottomNavRoute

    @Serializable
    data object Live : MainBottomNavRoute

    @Serializable
    data object Search : MainBottomNavRoute

    @Serializable
    data object History : MainBottomNavRoute
}