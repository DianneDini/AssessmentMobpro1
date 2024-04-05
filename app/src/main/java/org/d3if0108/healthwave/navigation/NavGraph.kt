package org.d3if0108.healthwave.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.d3if0108.healthwave.ui.screen.BmrScreen
import org.d3if0108.healthwave.ui.screen.MainScreen
import org.d3if0108.healthwave.ui.screen.NutritionScreen

@Composable
fun SetupNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ){
        composable(route = Screen.Home.route) {
             MainScreen(navController)
        }
        composable(route = Screen.Bmr.route) {
            BmrScreen(navController)
        }
        composable(route = Screen.Nutrition.route) {
            NutritionScreen(navController)
        }
    }
}