package io.github.manganoito.wordman.shared.repository

import io.github.manganoito.wordman.shared.data.WordDao
import io.github.manganoito.wordman.shared.model.Word
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class DefaultWordRepository @Inject constructor(
    private val wordDao: WordDao,
    private val coroutineContext: CoroutineContext = Dispatchers.IO,
) : WordRepository {
    override suspend fun addWord(word: Word): Unit = withContext(coroutineContext) {
        wordDao.insertWord(word)
    }

    override suspend fun getAllWords(): List<Word> = withContext(coroutineContext) {
        wordDao.getAllWords()
    }

    override suspend fun getSomeRandomWords(count: Int): List<Word> =
        withContext(coroutineContext) {
            wordDao.getSomeRandomWords(count)
        }

    override suspend fun getWordCount(): Int = withContext(coroutineContext) {
        wordDao.getWordCount()
    }

    override fun observeAllWords(): Flow<List<Word>> {
        return wordDao.observeAllWords()
    }
}
