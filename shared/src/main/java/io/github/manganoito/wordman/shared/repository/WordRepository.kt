package io.github.manganoito.wordman.shared.repository

import io.github.manganoito.wordman.shared.model.Word
import kotlinx.coroutines.flow.Flow

interface WordRepository {
    suspend fun addWord(word: Word)

    suspend fun addWord(
        word: String,
        meaning: String,
    ) = addWord(
        word = Word(
            id = 0,
            value = word,
            meaning = meaning,
        ),
    )

    suspend fun getAllWords(): List<Word>

    fun observeAllWords(): Flow<List<Word>>
}
