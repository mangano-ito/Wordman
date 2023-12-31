package io.github.manganoito.wordman.shared.repository

import javax.inject.Inject

class DefaultWordRepository @Inject constructor() : WordRepository {
    override suspend fun addWord(word: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllWords(): List<String> {
        TODO("Not yet implemented")
    }
}
