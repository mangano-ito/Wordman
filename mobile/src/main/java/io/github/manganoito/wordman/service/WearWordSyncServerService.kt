package io.github.manganoito.wordman.service

import dagger.hilt.android.AndroidEntryPoint
import io.github.manganoito.wordman.shared.data.WearWordSyncServerServiceBase
import io.github.manganoito.wordman.shared.data.WordSyncServerService
import io.github.manganoito.wordman.shared.data.proto.WordSyncServerServiceGrpcKt
import javax.inject.Inject

@AndroidEntryPoint
class WearWordSyncServerService : WearWordSyncServerServiceBase() {
    @Inject
    lateinit var service: WordSyncServerService

    override fun buildService(): WordSyncServerServiceGrpcKt.WordSyncServerServiceCoroutineImplBase {
        return service
    }
}
