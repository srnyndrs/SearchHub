package com.srnyndrs.android.searchhub.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.srnyndrs.android.searchhub.ui.screens.RepositoryViewModel
import com.srnyndrs.android.searchhub.ui.screens.detail.DetailScreen
import com.srnyndrs.android.searchhub.ui.screens.home.HomeScreen

@Composable
fun NavigationGraph(
    navController: NavHostController,
    viewModel: RepositoryViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screens.HomeScreen.route
    ) {
        composable(Screens.HomeScreen.route) {
            HomeScreen(navController = navController, viewModel = viewModel)
        }
        composable(
            Screens.DetailScreen.route + "/{repositoryIndex}",
            arguments = listOf(
                navArgument("repositoryIndex") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val repositoryIndex = backStackEntry.arguments!!.getInt("repositoryIndex")
            DetailScreen(navController = navController, viewModel = viewModel, repositoryIndex = repositoryIndex)
        }
    }
}