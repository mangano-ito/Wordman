package io.github.manganoito.wordman.shared.repository

import io.github.manganoito.wordman.shared.model.Word
import javax.inject.Inject

class DefaultWordRepository @Inject constructor() : WordRepository {
    override suspend fun addWord(word: Word) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllWords(): List<Word> {
        TODO("Not yet implemented")
    }
}
