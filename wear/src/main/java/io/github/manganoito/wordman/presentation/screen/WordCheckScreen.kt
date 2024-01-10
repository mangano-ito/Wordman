package io.github.manganoito.wordman.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
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
import io.github.manganoito.wordman.presentation.component.CorrectAnswerDialog
import io.github.manganoito.wordman.presentation.component.CorrectAnswerNotice
import io.github.manganoito.wordman.presentation.component.WrongAnswerDialog
import io.github.manganoito.wordman.presentation.theme.WordManPreviewTheme
import io.github.manganoito.wordman.shared.model.Word

@Composable
internal fun WordCheckScreen(
    viewModel: WordCheckScreenViewModel,
    onAnswerFinished: () -> Unit,
) {
    WordCheckScreen(
        state = viewModel.state,
        onAnswer = viewModel::answer,
        onAnswerFinished = onAnswerFinished,
    )
}

@Composable
private fun WordCheckScreen(
    state: WordCheckScreenUiState,
    onAnswer: (Word) -> Unit,
    onAnswerFinished: () -> Unit,
) {
    when (state) {
        is WordCheckScreenUiState.Loading -> {
            CircularProgressIndicator()
        }

        is WordCheckScreenUiState.Loaded -> {
            WordCheckScreenContent(
                question = state.question,
                answers = state.answers,
                onAnswer = onAnswer,
            )
        }

        is WordCheckScreenUiState.CorrectAnswer -> {
            CorrectAnswerDialog(
                onFinish = onAnswerFinished,
            )
        }

        is WordCheckScreenUiState.WrongAnswer -> {
            WrongAnswerDialog(
                onFinish = onAnswerFinished,
            )
        }
    }
}

@Composable
private fun WordCheckScreenContent(
    question: Word,
    answers: List<Word>,
    onAnswer: (Word) -> Unit,
    modifier: Modifier = Modifier,
) {
    ScalingLazyColumn(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        item {
            WordCheckHeader(
                word = question,
            )
        }
        item {
            WordCheckAnswerList(
                answers = answers,
                onAnswer = onAnswer,
            )
        }
    }
}

@Composable
private fun WordCheckHeader(
    word: Word,
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
            text = word.value,
            style = MaterialTheme.typography.display3,
        )
    }
}

@Composable
private fun WordCheckAnswerList(
    answers: List<Word>,
    onAnswer: (Word) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        answers.forEachIndexed { index, answer ->
            Chip(
                label = {
                    Text(text = answer.meaning)
                },
                onClick = { onAnswer(answer) },
                modifier = Modifier.fillMaxWidth(),
                icon = {
                    Text(text = "${index + 1}")
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
            question = Word(
                id = 1,
                value = "Word",
                meaning = "語，単語",
            ),
            answers = listOf(
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
        onAnswer = {},
        onAnswerFinished = {},
    )
}
