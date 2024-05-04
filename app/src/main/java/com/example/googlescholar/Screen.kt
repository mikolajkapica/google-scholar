package com.example.googlescholar

sealed class Screen(val route: String) {
    object HomeScreen : Screen("home")
    object SearchScreen : Screen("search")
    object ResultScreen : Screen("result")
}