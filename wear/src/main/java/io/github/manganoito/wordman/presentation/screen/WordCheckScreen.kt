package io.github.manganoito.wordman.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.AutoCenteringParams
import androidx.wear.compose.material.Card
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.CircularProgressIndicator
import androidx.wear.compose.material.ListHeader
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.ScalingLazyListScope
import androidx.wear.compose.material.ScalingLazyListState
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.material.Vignette
import androidx.wear.compose.material.VignettePosition
import androidx.wear.compose.material.itemsIndexed
import androidx.wear.compose.material.rememberScalingLazyListState
import androidx.wear.compose.material.scrollAway
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
    val listState = rememberScalingLazyListState()
    Scaffold(
        timeText = {
            TimeText(modifier = Modifier.scrollAway(listState))
        },
        vignette = {
            Vignette(vignettePosition = VignettePosition.TopAndBottom)
        },
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
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        wordCheckHeader(word = question)
        item {
            Spacer(modifier = Modifier.height(12.dp))
        }
        wordCheckAnswerList(
            answers = answers,
            onAnswer = onAnswer,
        )
    }
}

private fun ScalingLazyListScope.wordCheckHeader(
    word: Word,
) {
    item {
        ListHeader {
            Text(
                text = "Remember this word?",
                style = MaterialTheme.typography.caption2,
            )
        }
    }
    item {
        Text(
            text = word.value,
            style = MaterialTheme.typography.display3,
        )
    }
}

private fun ScalingLazyListScope.wordCheckAnswerList(
    answers: List<Word>,
    onAnswer: (Word) -> Unit,
) {
    itemsIndexed(answers) { index, answer ->
        WordCheckAnswer(
            answer = answer,
            index = index,
            onAnswer = { onAnswer(answer) },
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
private fun WordCheckAnswer(
    answer: Word,
    index: Int,
    onAnswer: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        onClick = onAnswer,
        modifier = modifier,
    ) {
        Text(text = answer.meaning)
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
