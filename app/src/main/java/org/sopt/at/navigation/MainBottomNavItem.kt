package org.sopt.at.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import org.sopt.at.R

internal enum class MainBottomNavItem(
    @DrawableRes val activeIcon: Int,
    @DrawableRes val inactiveIcon: Int,
    @StringRes val labelId: Int,
    val route: MainBottomNavRoute,
) {
    HOME(
        activeIcon = R.drawable.ic_home_active,
        inactiveIcon = R.drawable.ic_home_inactive,
        labelId = R.string.home_title,
        route = MainBottomNavRoute.Home,
    ),
    SHORTS(
        activeIcon = R.drawable.ic_shorts_active,
        inactiveIcon = R.drawable.ic_shorts_inactive,
        labelId = R.string.shorts_title,
        route = MainBottomNavRoute.Shorts,
    ),
    LIVE(
        activeIcon = R.drawable.ic_live_active,
        inactiveIcon = R.drawable.ic_live_inactive,
        labelId = R.string.live_title,
        route = MainBottomNavRoute.Live,
    ),
    SEARCH(
        activeIcon = R.drawable.ic_search_active,
        inactiveIcon = R.drawable.ic_search_inactive,
        labelId = R.string.search_title,
        route = MainBottomNavRoute.Search,
    ),
    HISTORY(
        activeIcon = R.drawable.ic_history_active,
        inactiveIcon = R.drawable.ic_history_inactive,
        labelId = R.string.history_title,
        route = MainBottomNavRoute.History,
    ),
}