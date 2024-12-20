package com.srnyndrs.android.searchhub.presentation.navigation

sealed class Screens(val route: String) {
    data object HomeScreen: Screens(route = "Home_Screen")
    data object DetailScreen: Screens(route = "Detail_Screen")
}