package io.github.manganoito.wordman.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.manganoito.wordman.shared.data.WordDao
import io.github.manganoito.wordman.shared.data.WordManDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): WordManDatabase {
        return WordManDatabase.getInstance(context)
    }

    @Provides
    fun provideWordDao(database: WordManDatabase): WordDao {
        return database.wordDao()
    }
}
