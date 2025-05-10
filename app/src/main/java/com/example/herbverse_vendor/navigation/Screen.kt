package com.example.herbverse_vendor.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Home : Screen("home")
    object Products : Screen("products")
    object Orders : Screen("orders")
    object Profile : Screen("profile")
}