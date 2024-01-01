package io.github.manganoito.wordman.ui.screen

data class AddWordScreenUiState(
    val word: String = "",
    val meaning: String = "",
) {
    val isValid: Boolean = word.isNotEmpty() && meaning.isNotEmpty()
}
