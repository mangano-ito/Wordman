package io.github.manganoito.wordman.ui.screen

import androidx.compose.runtime.Stable
import io.github.manganoito.wordman.shared.model.Word

@Stable
data class MainScreenUiState(
    val words: List<Word> = emptyList(),
)
