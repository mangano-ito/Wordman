package io.github.manganoito.wordman.presentation.theme

import androidx.compose.runtime.Composable
import androidx.wear.compose.material.MaterialTheme

@Composable
fun WordManTheme(
    content: @Composable () -> Unit,
) {
    /**
     * Empty theme to customize for your app.
     * See: https://developer.android.com/jetpack/compose/designsystems/custom
     */
    MaterialTheme(
        content = content,
    )
}

@Composable
fun WordManPreviewTheme(
    content: @Composable () -> Unit,
) = WordManTheme(content)
