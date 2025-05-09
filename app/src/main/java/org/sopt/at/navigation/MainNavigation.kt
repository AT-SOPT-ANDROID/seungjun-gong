package org.sopt.at.navigation

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import kotlinx.collections.immutable.persistentListOf
import org.sopt.at.ui.theme.TvingTheme
import org.sopt.at.utils.NoRippleInteractionSource

@Composable
fun MainNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState() // 상태
    val currentRoute = navBackStackEntry?.destination?.route

    val showBottomBar by remember(currentRoute) {
        derivedStateOf {
            MainBottomNavItem.entries.any { (it.route::class.qualifiedName ?: "") == currentRoute }
        }
    }

    if (showBottomBar) {
        NavigationBar(
            modifier = modifier,
            containerColor = Color.Black,
        ) {
            MainBottomNavItem.entries.forEach { item ->
                val selected = currentRoute == (item.route::class.qualifiedName ?: "")

                NavigationBarItem(
                    selected = selected,
                    interactionSource = NoRippleInteractionSource,
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color.Transparent,
                    ),
                    onClick = {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = ImageVector.vectorResource(
                                id = if (selected) item.activeIcon else item.inactiveIcon
                            ),
                            contentDescription = stringResource(id = item.labelId),
                            modifier = Modifier
                                .size(42.dp),
                            tint = if (selected) Color.White else TvingTheme.colors.inactiveGray,
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

}