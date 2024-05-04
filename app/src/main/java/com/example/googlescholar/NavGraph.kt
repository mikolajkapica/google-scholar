package com.example.googlescholar

import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route,
        enterTransition = { fadeIn(animationSpec = TweenSpec(0)) },
        exitTransition = { fadeOut(animationSpec = TweenSpec(0)) }
    ) {
        composable(Screen.HomeScreen.route) {
            Home(navController)
        }
        composable(Screen.SearchScreen.route) {
            Search(navController)
        }
        composable(Screen.ResultScreen.route) {
            Result(navController)
        }
    }
}