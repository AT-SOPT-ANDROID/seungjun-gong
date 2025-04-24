package org.sopt.at.presentation.main.navigation

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import org.sopt.at.ui.theme.TvingTheme
import org.sopt.at.utils.NoRippleInteractionSource

@Composable
fun MainNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState() // 상태
    val currentRoute = navBackStackEntry?.destination?.route

    val items = listOf(
        MainNavItem.HOME,
        MainNavItem.SHORTS,
        MainNavItem.LIVE,
        MainNavItem.SEARCH,
        MainNavItem.HISTORY,
    )

    NavigationBar(
        modifier = modifier,
        containerColor = Color.Black,
    ) {
        items.forEach { item ->
            val selected = currentRoute == item.screenRoute::class.qualifiedName

            NavigationBarItem(
                selected = selected,
                interactionSource = NoRippleInteractionSource,
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent,
                ),
                onClick = {
                    navController.navigate(item.screenRoute) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(
                            id = if (selected) item.activeIcon else item.inactiveIcon
                        ),
                        contentDescription = null,
                        modifier = Modifier
                            .size(42.dp),
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = item.labelId),
                        color = if (selected) Color.White else TvingTheme.colors.inactiveGray,
                        fontWeight = FontWeight.W500,
                        fontSize = 10.sp,
                    )
                }
            )
        }
    }
}