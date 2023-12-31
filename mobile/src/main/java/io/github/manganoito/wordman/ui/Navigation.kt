package io.github.manganoito.wordman.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Route.Main.path,
    ) {
        composable(Route.Main.path) {
            MainScreen(
                onAddNewWordButtonClick = {
                    navController.navigate(Route.AddWord.path)
                },
            )
        }
        composable(Route.AddWord.path) {
            AddWordScreen()
        }
    }
}

enum class Route(val path: String) {
    Main("main"),
    AddWord("add_word"),
}
