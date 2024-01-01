package io.github.manganoito.wordman.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.Text
import io.github.manganoito.wordman.presentation.theme.WordManPreviewTheme

@Composable
internal fun MainScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Chip(
            label = {
                Text(text = "Try Word Check Now")
            },
            onClick = { /*TODO*/ },
        )
    }
}

@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
private fun MainScreenPreview() = WordManPreviewTheme {
    MainScreen()
}
