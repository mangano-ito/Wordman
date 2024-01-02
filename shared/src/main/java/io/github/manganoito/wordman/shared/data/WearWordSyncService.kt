package io.github.manganoito.wordman.shared.data

import androidx.lifecycle.lifecycleScope
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.data.WearDataLayerRegistry
import com.google.android.horologist.datalayer.grpc.server.BaseGrpcDataService
import io.github.manganoito.wordman.shared.data.proto.WordSyncServiceGrpcKt

class WearWordSyncService :
    BaseGrpcDataService<WordSyncServiceGrpcKt.WordSyncServiceCoroutineImplBase>() {
    @ExperimentalHorologistApi
    override val registry: WearDataLayerRegistry by lazy {
        WearDataLayerRegistry.fromContext(
            application = applicationContext,
            coroutineScope = lifecycleScope,
        ).apply {
            registerSerializer(WordSyncDataSerializer)
        }
    }

    override fun buildService(): WordSyncServiceGrpcKt.WordSyncServiceCoroutineImplBase {
        return WordSyncService()
    }
}