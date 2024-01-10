package io.github.manganoito.wordman.presentation.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.material.Vignette
import androidx.wear.compose.material.VignettePosition
import androidx.wear.compose.material.rememberScalingLazyListState
import androidx.wear.compose.material.scrollAway
import io.github.manganoito.wordman.presentation.theme.WordManPreviewTheme

@Composable
internal fun MainScreen(
    viewModel: MainScreenViewModel,
    onWordCheckButtonClick: () -> Unit,
) {
    val state by viewModel.state.collectAsState()
    MainScreen(
        state = state,
        onWordCheckButtonClick = onWordCheckButtonClick,
    )
}

@Composable
private fun MainScreen(
    state: MainScreenUiState,
    onWordCheckButtonClick: () -> Unit,
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
        ScalingLazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                Chip(
                    label = {
                        Text(
                            text = "Try Vocabulary Check Now",
                            textAlign = TextAlign.Center,
                        )
                    },
                    onClick = onWordCheckButtonClick,
                )
            }
        }
    }
}

@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
private fun MainScreenPreview() = WordManPreviewTheme {
    MainScreen(
        state = MainScreenUiState(),
        onWordCheckButtonClick = {},
    )
}
