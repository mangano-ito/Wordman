package io.github.manganoito.wordman.shared.data

import com.google.protobuf.Empty
import com.google.protobuf.Timestamp
import io.github.manganoito.wordman.shared.data.proto.WordSyncProtoData.WordSyncData
import io.github.manganoito.wordman.shared.data.proto.WordSyncServiceGrpcKt

class WordSyncService : WordSyncServiceGrpcKt.WordSyncServiceCoroutineImplBase() {
    override suspend fun getRandomWord(request: Empty): WordSyncData {
        return WordSyncData.newBuilder()
            .setValue("test")
            .setMeaning("〔機器や製法などの〕検査、試験運転、動作確認")
            .setUpdated(
                System.currentTimeMillis().toProtoTimestamp(),
            )
            .build()
    }

    override suspend fun notifyNewWord(request: WordSyncData): Empty {
        return super.notifyNewWord(request)
    }
}

private fun Long.toProtoTimestamp(): Timestamp = Timestamp.newBuilder()
    .setSeconds(this / 1000)
    .setNanos((this % 1000).toInt() * 1000000)
    .build()
