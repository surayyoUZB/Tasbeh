package com.yoo.tasbeh.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.yoo.tasbeh.ui.SplashScreen
import com.yoo.tasbeh.ui.bottom.menu.viewModel.MainViewModel
import com.yoo.tasbeh.ui.main.MainScreen
import com.yoo.tasbeh.util.Graph

@Composable
fun RootNavigation(
    navHostController: NavHostController,
    viewModel: MainViewModel= hiltViewModel()
) {
    NavHost(
        route = Graph.ROOT,
        navController = navHostController,
        startDestination = Graph.SPLASH
    ){
        splashGraph(navHostController)
        composable(route = Graph.MAIN){
            MainScreen(viewModel = viewModel)
        }
    }


}

fun NavGraphBuilder.splashGraph(navHostController: NavHostController) {
    navigation(
        route = Graph.SPLASH,
        startDestination = "splash"
    ) {
        composable(route = "splash") {
            SplashScreen(navHostController)
        }
    }
}