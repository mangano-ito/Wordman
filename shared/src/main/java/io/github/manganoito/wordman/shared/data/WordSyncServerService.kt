package io.github.manganoito.wordman.shared.data

import com.google.protobuf.Empty
import com.google.protobuf.Timestamp
import io.github.manganoito.wordman.shared.data.proto.WordSyncProtoData.WordData
import io.github.manganoito.wordman.shared.data.proto.WordSyncServerServiceGrpcKt

class WordSyncServerService : WordSyncServerServiceGrpcKt.WordSyncServerServiceCoroutineImplBase() {
    override suspend fun getRandomWord(request: Empty): WordData {
        return WordData.newBuilder()
            .setValue("test")
            .setMeaning("〔機器や製法などの〕検査、試験運転、動作確認")
            .setUpdated(
                System.currentTimeMillis().toProtoTimestamp(),
            )
            .build()
    }
}

private fun Long.toProtoTimestamp(): Timestamp = Timestamp.newBuilder()
    .setSeconds(this / 1000)
    .setNanos((this % 1000).toInt() * 1000000)
    .build()
