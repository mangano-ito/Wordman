package io.github.manganoito.wordman.shared.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.github.manganoito.wordman.shared.model.Word
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {
    @Insert
    fun insertWord(word: Word)

    @Query("SELECT * FROM word")
    fun getAllWords(): List<Word>

    @Query("SELECT * FROM word")
    fun observeAllWords(): Flow<List<Word>>
}
