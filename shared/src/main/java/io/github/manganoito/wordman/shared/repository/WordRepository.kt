package io.github.manganoito.wordman.shared.repository

import io.github.manganoito.wordman.shared.model.Word

interface WordRepository {
    suspend fun addWord(word: Word)
    suspend fun getAllWords(): List<Word>
}
