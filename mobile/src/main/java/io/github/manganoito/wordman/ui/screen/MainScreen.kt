package io.github.manganoito.wordman.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.manganoito.wordman.shared.model.Word
import io.github.manganoito.wordman.ui.component.WordCardList
import io.github.manganoito.wordman.ui.theme.WordManPreviewTheme

@Composable
internal fun MainScreen(
    viewModel: MainScreenViewModel,
    onAddNewWordButtonClick: () -> Unit,
) {
    val words by viewModel.words
    MainScreen(
        words = words,
        onAddNewWordButtonClick = onAddNewWordButtonClick,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MainScreen(
    words: List<Word>,
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
            words = words,
            modifier = Modifier.padding(paddingValues),
        )
    }
}

@Composable
private fun MainScreenContent(
    words: List<Word>,
    modifier: Modifier = Modifier,
) {
    if (words.isEmpty()) {
        EmptyWordCardList(
            modifier = modifier,
        )
    } else {
        WordCardList(
            words = words,
            modifier = modifier,
        )
    }
}

@Composable
private fun EmptyWordCardList(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "No Words",
            style = MaterialTheme.typography.bodyLarge,
            color = LocalContentColor.current.copy(
                alpha = 0.6f,
            ),
        )
    }
}

@Preview
@Composable
private fun MainScreenPreview() = WordManPreviewTheme {
    MainScreen(
        words = listOf(
            Word(
                id = 1,
                value = "Word",
                meaning = "語，単語",
            ),
            Word(
                id = 2,
                value = "Phrase",
                meaning = "成句，熟語，慣用句，決まり文句",
            ),
        ),
        onAddNewWordButtonClick = {},
    )
}

@Preview
@Composable
private fun MainScreenNoWordsPreview() = WordManPreviewTheme {
    MainScreen(
        words = emptyList(),
        onAddNewWordButtonClick = {},
    )
}
