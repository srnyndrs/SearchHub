package com.srnyndrs.android.searchhub.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.srnyndrs.android.searchhub.presentation.RepositoryViewModel
import com.srnyndrs.android.searchhub.presentation.detail_screen.DetailScreen
import com.srnyndrs.android.searchhub.presentation.home_screen.HomeScreen

@Composable
fun NavigationGraph(
    navController: NavHostController
) {

    val viewModel = hiltViewModel<RepositoryViewModel>()

    NavHost(
        navController = navController,
        startDestination = Screens.HomeScreen.route
    ) {
        // Home screen
        composable(Screens.HomeScreen.route) {
            HomeScreen(
                items = viewModel.repositories.collectAsLazyPagingItems(),
                onSearch = viewModel::fetchRepositories,
                onClear = viewModel::clearRepositories,
                onNavigation = { repository ->
                    viewModel.selectedRepository = repository
                    navController.navigate(Screens.DetailScreen.route)
                }
            )
        }
        // Detail screen
        composable(Screens.DetailScreen.route) {
            DetailScreen(
                repository = viewModel.selectedRepository,
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}