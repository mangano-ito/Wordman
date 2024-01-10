package io.github.manganoito.wordman.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
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
            MainScreen(
                viewModel = hiltViewModel(),
                onWordCheckButtonClick = {
                    navController.navigate(Route.WordCheck.path)
                },
            )
        }
        composable(Route.WordCheck.path) {
            WordCheckScreen(
                viewModel = hiltViewModel(),
                onAnswerFinished = {
                    navController.popBackStack()
                },
            )
        }
    }
}

enum class Route(val path: String) {
    Main("main"),
    WordCheck("word_check"),
}
