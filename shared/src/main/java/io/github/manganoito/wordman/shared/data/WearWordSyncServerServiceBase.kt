package io.github.manganoito.wordman.shared.data

import androidx.lifecycle.lifecycleScope
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.data.WearDataLayerRegistry
import com.google.android.horologist.datalayer.grpc.server.BaseGrpcDataService
import io.github.manganoito.wordman.shared.data.proto.WordSyncServerServiceGrpcKt

abstract class WearWordSyncServerServiceBase :
    BaseGrpcDataService<WordSyncServerServiceGrpcKt.WordSyncServerServiceCoroutineImplBase>() {

    @ExperimentalHorologistApi
    override val registry: WearDataLayerRegistry by lazy {
        createWearDataLayerRegistry(
            applicationContext = applicationContext,
            coroutineScope = lifecycleScope,
        )
    }
}
