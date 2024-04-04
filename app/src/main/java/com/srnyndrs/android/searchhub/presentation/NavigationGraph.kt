package com.srnyndrs.android.searchhub.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.srnyndrs.android.searchhub.domain.navigation.Screens
import com.srnyndrs.android.searchhub.presentation.detail_screen.DetailScreen
import com.srnyndrs.android.searchhub.presentation.home_screen.HomeScreen

@Composable
fun NavigationGraph(
    navController: NavHostController,
    viewModel: RepositoryViewModel
) {
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
                onNavigation = {
                    it?.let {
                        viewModel.selectRepository(it)
                        navController.navigate(Screens.DetailScreen.route)
                    }
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