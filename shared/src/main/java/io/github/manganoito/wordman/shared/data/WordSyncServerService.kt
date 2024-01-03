package io.github.manganoito.wordman.shared.data

import com.google.protobuf.Empty
import com.google.protobuf.Timestamp
import io.github.manganoito.wordman.shared.data.proto.WordSyncProtoData
import io.github.manganoito.wordman.shared.data.proto.WordSyncProtoData.WordCountData
import io.github.manganoito.wordman.shared.data.proto.WordSyncProtoData.WordData
import io.github.manganoito.wordman.shared.data.proto.WordSyncServerServiceGrpcKt
import io.github.manganoito.wordman.shared.model.Word
import io.github.manganoito.wordman.shared.repository.WordRepository

class WordSyncServerService(
    private val wordRepository: WordRepository,
) : WordSyncServerServiceGrpcKt.WordSyncServerServiceCoroutineImplBase() {
    override suspend fun getRandomWord(request: Empty): WordData {
        val word = wordRepository.getSomeRandomWords(1).firstOrNull()
        return word?.toWordData() ?: emptyWordData
    }

    override suspend fun getRandomWords(request: WordSyncProtoData.RandomWordRequest): WordSyncProtoData.RandomWordResponse {
        val words = wordRepository.getSomeRandomWords(request.count)
        return WordSyncProtoData.RandomWordResponse.newBuilder()
            .addAllWords(words.map { it.toWordData() })
            .build()
    }

    override suspend fun getWordCount(request: Empty): WordCountData {
        val count = wordRepository.getWordCount()
        return WordCountData.newBuilder()
            .setCount(count.toLong())
            .build()
    }
}

private val emptyWordData = WordData.newBuilder()
    .setValue("test")
    .setMeaning("〔機器や製法などの〕検査、試験運転、動作確認")
    .setUpdated(
        System.currentTimeMillis().toProtoTimestamp(),
    )
    .build()

private fun Word.toWordData(): WordData = WordData.newBuilder()
    .setValue(value)
    .setMeaning(meaning)
    .setUpdated(
        System.currentTimeMillis().toProtoTimestamp(),
    )
    .build()

private fun Long.toProtoTimestamp(): Timestamp = Timestamp.newBuilder()
    .setSeconds(this / 1000)
    .setNanos((this % 1000).toInt() * 1000000)
    .build()
