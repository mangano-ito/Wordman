package io.github.manganoito.wordman.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.manganoito.wordman.shared.data.WordDao
import io.github.manganoito.wordman.shared.repository.DefaultWordRepository
import io.github.manganoito.wordman.shared.repository.WordRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideWordRepository(
        wordDao: WordDao,
    ): WordRepository = DefaultWordRepository(
        wordDao = wordDao,
        coroutineContext = Dispatchers.IO,
    )
}
