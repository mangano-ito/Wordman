package io.github.manganoito.wordman.shared.repository

import io.github.manganoito.wordman.shared.model.Word
import kotlinx.coroutines.flow.Flow

interface WordRepository {
    suspend fun addWord(word: Word)
    suspend fun getAllWords(): List<Word>

    fun observeAllWords(): Flow<List<Word>>
}
