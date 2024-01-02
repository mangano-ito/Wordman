package io.github.manganoito.wordman.di

import android.content.Context
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.data.WearDataLayerRegistry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ServiceScoped
import io.github.manganoito.wordman.shared.data.WordSyncDataSerializer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

@Module
@InstallIn(ServiceComponent::class)
class WordSyncServiceModule {
    @Provides
    @ServiceScoped
    fun provideCoroutineScope(): CoroutineScope {
        return CoroutineScope(SupervisorJob() + Dispatchers.IO)
    }

    @Provides
    @ServiceScoped
    @OptIn(ExperimentalHorologistApi::class)
    fun provideWearDataLayerRegistry(
        @ApplicationContext applicationContext: Context,
        coroutineScope: CoroutineScope,
    ): WearDataLayerRegistry {
        return WearDataLayerRegistry.fromContext(
            application = applicationContext,
            coroutineScope = coroutineScope,
        ).apply {
            registerSerializer(WordSyncDataSerializer)
        }
    }
}
