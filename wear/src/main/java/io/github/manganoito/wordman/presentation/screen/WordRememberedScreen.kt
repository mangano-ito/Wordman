package io.github.manganoito.wordman.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.material.CircularProgressIndicator
import androidx.wear.compose.material.dialog.Confirmation
import androidx.wear.compose.material.dialog.Dialog
import io.github.manganoito.wordman.presentation.component.WordRememberedNotice
import io.github.manganoito.wordman.presentation.theme.WordManPreviewTheme

@Composable
internal fun WordRememberedScreen(
    viewModel: WordRememberedScreenViewModel,
    onFinish: () -> Unit,
) {
    WordRememberedScreen(
        state = viewModel.state,
        onFinish = onFinish,
    )
}

@Composable
private fun WordRememberedScreen(
    state: WordRememberedScreenUiState,
    onFinish: () -> Unit,
) {
    when (state) {
        is WordRememberedScreenUiState.Loading -> {
            CircularProgressIndicator()
        }

        is WordRememberedScreenUiState.Loaded -> {
            WordRememberedScreen(
                state = state,
                onFinish = onFinish,
            )
        }
    }
}

@Composable
private fun WordRememberedScreen(
    state: WordRememberedScreenUiState.Loaded,
    onFinish: () -> Unit,
) {
    Box(
        contentAlignment = Alignment.Center,
    ) {
        var showDialog by rememberSaveable { mutableStateOf(true) }
        Dialog(
            showDialog = showDialog,
            onDismissRequest = {
                showDialog = false
                onFinish()
            },
        ) {
            Confirmation(
                onTimeout = {
                    showDialog = false
                    onFinish()
                },
            ) {
                WordRememberedNotice()
            }
        }
    }
}

@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
private fun WordRememberedScreenPreview() = WordManPreviewTheme {
    WordRememberedScreen(
        state = WordRememberedScreenUiState.Loaded(
            wordCount = 1,
        ),
        onFinish = {},
    )
}
