package io.github.manganoito.wordman.shared.repository

import io.github.manganoito.wordman.shared.data.WordDao
import io.github.manganoito.wordman.shared.model.Word
import javax.inject.Inject

class DefaultWordRepository @Inject constructor(
    private val wordDao: WordDao,
) : WordRepository {
    override suspend fun addWord(word: Word) {
        wordDao.insertWord(word)
    }

    override suspend fun getAllWords(): List<Word> {
        return wordDao.getAllWords()
    }
}
