package io.github.manganoito.wordman.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.manganoito.wordman.shared.model.Word
import io.github.manganoito.wordman.ui.component.WordCard
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
    contentPadding: PaddingValues = PaddingValues(
        horizontal = 16.dp,
    ),
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(words) { word ->
            WordCard(
                word = word.value,
                meaning = word.meaning,
            )
        }
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
