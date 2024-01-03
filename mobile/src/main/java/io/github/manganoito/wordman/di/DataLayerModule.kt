package io.github.manganoito.wordman.di

import android.content.Context
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.data.WearDataLayerRegistry
import com.google.android.horologist.datalayer.phone.PhoneDataLayerAppHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.ActivityRetainedLifecycle
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import io.github.manganoito.wordman.shared.data.WordDataSerializer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

@Module
@InstallIn(ActivityRetainedComponent::class)
class DataLayerModule {
    @Provides
    @ActivityRetainedScoped
    fun provideCoroutineScope(
        lifecycle: ActivityRetainedLifecycle,
    ): CoroutineScope {
        return CoroutineScope(SupervisorJob() + Dispatchers.Default).also {
            lifecycle.addOnClearedListener {
                it.cancel()
            }
        }
    }

    @Provides
    @ActivityRetainedScoped
    @OptIn(ExperimentalHorologistApi::class)
    fun provideWearDataLayerRegistry(
        @ApplicationContext applicationContext: Context,
        coroutineScope: CoroutineScope,
    ): WearDataLayerRegistry {
        return WearDataLayerRegistry.fromContext(
            application = applicationContext,
            coroutineScope = coroutineScope,
        ).apply {
            registerSerializer(WordDataSerializer)
        }
    }

    @Provides
    @ActivityRetainedScoped
    @OptIn(ExperimentalHorologistApi::class)
    fun providePhoneDataLayerAppHelper(
        @ApplicationContext applicationContext: Context,
        wearDataLayerRegistry: WearDataLayerRegistry,
    ): PhoneDataLayerAppHelper {
        return PhoneDataLayerAppHelper(
            context = applicationContext,
            registry = wearDataLayerRegistry,
        )
    }
}
