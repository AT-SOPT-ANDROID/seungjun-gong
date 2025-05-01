package org.sopt.at.navigation

import kotlinx.serialization.Serializable

sealed interface MainNavRoute {
    val route: String

    @Serializable
    data object Home : MainNavRoute{
        override val route = "home"
    }

    @Serializable
    data object Shorts : MainNavRoute{
        override val route = "shorts"
    }

    @Serializable
    data object Live : MainNavRoute{
        override val route = "live"
    }

    @Serializable
    data object Search : MainNavRoute{
        override val route = "search"
    }

    @Serializable
    data object History : MainNavRoute{
        override val route = "history"
    }

    @Serializable
    data object My : MainNavRoute{
        override val route = "my"
    }

    @Serializable
    data object SignIn : MainNavRoute{
        override val route = "signin"
    }

    @Serializable
    data object SignUp : MainNavRoute{
        override val route = "signup"
    }
}

