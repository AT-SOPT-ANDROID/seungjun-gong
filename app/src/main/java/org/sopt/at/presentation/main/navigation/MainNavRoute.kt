package org.sopt.at.presentation.main.navigation

import kotlinx.serialization.Serializable

interface MainNavRoute

@Serializable
data object Home : MainNavRoute

@Serializable
data object Shorts : MainNavRoute

@Serializable
data object Live : MainNavRoute

@Serializable
data object Search : MainNavRoute

@Serializable
data object History : MainNavRoute
