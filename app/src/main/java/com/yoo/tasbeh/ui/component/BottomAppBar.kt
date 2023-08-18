package com.yoo.tasbeh.ui.component

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.yoo.tasbeh.ui.theme.white

@Composable
fun BottomAppBar(navHostController: NavHostController) {
    val screens = listOf(
        BottomBar.Home,
        BottomBar.Menu,
        BottomBar.Favourite,
        BottomBar.Quiz
    )
    val currentDestination by navHostController.currentBackStackEntryAsState()
    val isBottomBarVisible = screens.any { it.route == currentDestination?.destination?.route }

    if (isBottomBarVisible) {
        NavigationBar(
            containerColor = MaterialTheme.colorScheme.primary
        ) {
            screens.forEach { screen ->
                NavigationBarItem(
                    selected = screen.route == currentDestination?.destination?.route,
                    onClick = {
                        navHostController.navigate(screen.route) {
                            popUpTo(navHostController.graph.findStartDestination().id) {
                                inclusive = true
                            }
                        }
                    },
                    icon = {
                        if (screen.icon2 == 0) {
                            Icon(
                                imageVector = screen.icon,
                                contentDescription = screen.route,
                                tint = white
                            )
                        } else {
                            Icon(
                                modifier = Modifier.size(25.dp),
                                painter = painterResource(id = screen.icon2),
                                contentDescription = screen.route,
                                tint = white
                            )
                        }
                    }

                )
            }
        }
    }

}