package io.github.manganoito.wordman.shared.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.github.manganoito.wordman.shared.model.Word

@Database(
    entities = [
        Word::class,
    ],
    version = 1,
)
abstract class WordManDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao

    companion object {
        const val DATABASE_NAME = "wordman.db"

        @Volatile
        private var instance: WordManDatabase? = null

        fun getInstance(context: Context): WordManDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): WordManDatabase {
            return Room.databaseBuilder(context, WordManDatabase::class.java, DATABASE_NAME)
                .build()
        }
    }
}
