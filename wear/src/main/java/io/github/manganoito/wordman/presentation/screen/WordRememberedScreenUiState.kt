package io.github.manganoito.wordman.presentation.screen

sealed interface WordRememberedScreenUiState {
    data object Loading : WordRememberedScreenUiState
    data class Loaded(
        val wordCount: Long,
    ) : WordRememberedScreenUiState
}
