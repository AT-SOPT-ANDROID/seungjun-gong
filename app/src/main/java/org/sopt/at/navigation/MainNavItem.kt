package org.sopt.at.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import org.sopt.at.R
import org.sopt.at.navigation.MainNavRoute.History
import org.sopt.at.navigation.MainNavRoute.Home
import org.sopt.at.navigation.MainNavRoute.Live
import org.sopt.at.navigation.MainNavRoute.Search
import org.sopt.at.navigation.MainNavRoute.Shorts

internal enum class MainNavItem(
    @DrawableRes val activeIcon: Int,
    @DrawableRes val inactiveIcon: Int,
    @StringRes val labelId: Int,
    val screenRoute: MainNavRoute,
) {
    HOME(
        activeIcon = R.drawable.ic_home_active,
        inactiveIcon = R.drawable.ic_home_inactive,
        labelId = R.string.home_title,
        screenRoute = Home,
    ),
    SHORTS(
        activeIcon = R.drawable.ic_shorts_active,
        inactiveIcon = R.drawable.ic_shorts_inactive,
        labelId = R.string.shorts_title,
        screenRoute = Shorts,
    ),
    LIVE(
        activeIcon = R.drawable.ic_live_active,
        inactiveIcon = R.drawable.ic_live_inactive,
        labelId = R.string.live_title,
        screenRoute = Live,
    ),
    SEARCH(
        activeIcon = R.drawable.ic_search_active,
        inactiveIcon = R.drawable.ic_search_inactive,
        labelId = R.string.search_title,
        screenRoute = Search,
    ),
    HISTORY(
        activeIcon = R.drawable.ic_history_active,
        inactiveIcon = R.drawable.ic_history_inactive,
        labelId = R.string.history_title,
        screenRoute = History,
    ),
}