package io.github.manganoito.wordman.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.CircularProgressIndicator
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.Text
import io.github.manganoito.wordman.presentation.theme.WordManPreviewTheme
import io.github.manganoito.wordman.shared.model.Word

@Composable
internal fun WordCheckScreen(
    viewModel: WordCheckScreenViewModel,
) {
    LaunchedEffect(Unit) {
        viewModel.doSync()
    }
    WordCheckScreen(
        state = viewModel.state,
    )
}

@Composable
private fun WordCheckScreen(
    state: WordCheckScreenUiState,
) {
    when (state) {
        is WordCheckScreenUiState.Loading -> {
            CircularProgressIndicator()
        }

        is WordCheckScreenUiState.Loaded -> {
            WordCheckScreenContent(
                words = state.words,
            )
        }
    }
}

@Composable
private fun WordCheckScreenContent(
    words: List<Word>,
    modifier: Modifier = Modifier,
) {
    val answers by remember(words) {
        mutableStateOf(
            words.map { it.meaning }.shuffled(),
        )
    }
    ScalingLazyColumn(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        item {
            WordCheckHeader(
                word = words.first().value,
            )
        }
        item {
            WordCheckAnswerList(
                answers = answers,
                onAnswer = { _ -> },
            )
        }
    }
}

@Composable
private fun WordCheckHeader(
    word: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Remember this word?",
            style = MaterialTheme.typography.caption2,
        )
        Text(
            text = word,
            style = MaterialTheme.typography.display3,
        )
    }
}

@Composable
private fun WordCheckAnswerList(
    answers: List<String>,
    onAnswer: (index: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        answers.forEachIndexed { index, answer ->
            Chip(
                onClick = { onAnswer(index) },
                label = {
                    Text(text = "${index + 1}: $answer")
                },
            )
        }
    }
}

@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
private fun WordCheckScreenPreview() = WordManPreviewTheme {
    WordCheckScreen(
        state = WordCheckScreenUiState.Loaded(
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
        ),
    )
}
