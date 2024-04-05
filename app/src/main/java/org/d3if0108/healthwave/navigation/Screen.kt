package org.d3if0108.healthwave.navigation

sealed class Screen(val route: String) {
    data object Home: Screen("mainScreen")
    data object Bmr: Screen("bmrScreen")
    data object Nutrition: Screen("nutritionScreen")
}