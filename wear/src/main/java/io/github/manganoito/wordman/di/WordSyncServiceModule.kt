package io.github.manganoito.wordman.di

import android.content.Context
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.data.TargetNodeId
import com.google.android.horologist.data.WearDataLayerRegistry
import com.google.android.horologist.datalayer.grpc.GrpcExtensions.grpcClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.ActivityRetainedLifecycle
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import io.github.manganoito.wordman.shared.data.createWearDataLayerRegistry
import io.github.manganoito.wordman.shared.data.proto.WordSyncServerServiceGrpcKt
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

@Module
@InstallIn(ActivityRetainedComponent::class)
class WordSyncServiceModule {
    @Provides
    @ActivityRetainedScoped
    fun provideWearDataLayerCoroutineScope(
        activityRetainedLifecycle: ActivityRetainedLifecycle,
    ): CoroutineScope {
        return CoroutineScope(
            SupervisorJob() + Dispatchers.Default,
        ).also {
            activityRetainedLifecycle.addOnClearedListener {
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
        return createWearDataLayerRegistry(
            applicationContext = applicationContext,
            coroutineScope = coroutineScope,
        )
    }

    @Provides
    @ActivityRetainedScoped
    @OptIn(ExperimentalHorologistApi::class)
    fun provideWordSyncClient(
        registry: WearDataLayerRegistry,
        coroutineScope: CoroutineScope,
    ): WordSyncServerServiceGrpcKt.WordSyncServerServiceCoroutineStub {
        return registry.grpcClient(
            nodeId = TargetNodeId.PairedPhone,
            coroutineScope = coroutineScope,
        ) {
            WordSyncServerServiceGrpcKt.WordSyncServerServiceCoroutineStub(it)
        }
    }
}
