package io.github.manganoito.wordman.shared.repository

interface WordRepository {
    suspend fun addWord(word: String)
    suspend fun getAllWords(): List<String>
}
