package io.github.manganoito.wordman.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.manganoito.wordman.ui.theme.WordManPreviewTheme

@Composable
internal fun MainScreen(
    viewModel: MainScreenViewModel,
    onAddNewWordButtonClick: () -> Unit,
) {
    MainScreen(
        onAddNewWordButtonClick = onAddNewWordButtonClick,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MainScreen(
    onAddNewWordButtonClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "App")
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddNewWordButtonClick,
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add New Word",
                )
            }
        },
    ) { paddingValues ->
        MainScreenContent(
            modifier = Modifier.padding(paddingValues),
        )
    }
}

@Composable
private fun MainScreenContent(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(text = "Main Screen")
    }
}

@Preview
@Composable
private fun MainScreenPreview() = WordManPreviewTheme {
    MainScreen(
        onAddNewWordButtonClick = {},
    )
}
