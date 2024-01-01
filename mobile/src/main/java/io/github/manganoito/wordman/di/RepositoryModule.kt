package io.github.manganoito.wordman.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.manganoito.wordman.shared.repository.DefaultWordRepository
import io.github.manganoito.wordman.shared.repository.WordRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindWordRepository(impl: DefaultWordRepository): WordRepository
}
