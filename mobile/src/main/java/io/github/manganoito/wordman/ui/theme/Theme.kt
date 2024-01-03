package io.github.manganoito.wordman.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val wordManLightColorScheme = lightColorScheme()

@Composable
fun WordManTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = wordManLightColorScheme,
    ) {
        content()
    }
}

@Composable
internal fun WordManPreviewTheme(content: @Composable () -> Unit) {
    WordManTheme {
        content()
    }
}
