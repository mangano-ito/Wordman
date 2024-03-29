package io.github.manganoito.wordman.presentation.screen

import io.github.manganoito.wordman.shared.model.Word

sealed interface WordCheckScreenUiState {
    data object Loading : WordCheckScreenUiState
    data class Loaded(
        val question: Word,
        val answers: List<Word>,
    ) : WordCheckScreenUiState
    data object CorrectAnswer : WordCheckScreenUiState
    data object WrongAnswer : WordCheckScreenUiState
}
