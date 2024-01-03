package io.github.manganoito.wordman.di

import android.content.Context
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.data.WearDataLayerRegistry
import com.google.android.horologist.datalayer.watch.WearDataLayerAppHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.CoroutineScope

@Module
@InstallIn(ActivityRetainedComponent::class)
class DataLayerModule {
    @Provides
    @ActivityRetainedScoped
    @OptIn(ExperimentalHorologistApi::class)
    fun provideWearDataLayerAppHelper(
        @ApplicationContext applicationContext: Context,
        wearDataLayerRegistry: WearDataLayerRegistry,
        coroutineScope: CoroutineScope,
    ): WearDataLayerAppHelper {
        return WearDataLayerAppHelper(
            context = applicationContext,
            registry = wearDataLayerRegistry,
            scope = coroutineScope,
        )
    }
}
