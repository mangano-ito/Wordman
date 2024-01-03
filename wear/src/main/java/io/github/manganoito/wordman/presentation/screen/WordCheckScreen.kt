package io.github.manganoito.wordman.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.Text
import io.github.manganoito.wordman.presentation.theme.WordManPreviewTheme

@Composable
private fun WordCheckScreen() {
    ScalingLazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        item {
            WordCheckHeader(
                word = "Horologist",
            )
        }
        item {
            WordCheckAnswerList(
                answers = listOf("時計学者、時計工", "時計学者、時計工", "時計学者、時計工"),
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
    WordCheckScreen()
}
