package io.github.manganoito.wordman.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.github.manganoito.wordman.presentation.screen.MainScreen
import io.github.manganoito.wordman.presentation.screen.WordCheckScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Route.Main.path) {
        composable(Route.Main.path) {
            MainScreen()
        }
        composable(Route.WordCheck.path) {
            WordCheckScreen()
        }
    }
}

enum class Route(val path: String) {
    Main("main"),
    WordCheck("word_check"),
}
