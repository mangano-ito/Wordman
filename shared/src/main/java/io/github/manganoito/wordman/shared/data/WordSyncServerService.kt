package io.github.manganoito.wordman.shared.data

import com.google.protobuf.Empty
import io.github.manganoito.wordman.shared.data.proto.WordSyncProtoData
import io.github.manganoito.wordman.shared.data.proto.WordSyncProtoData.WordCountData
import io.github.manganoito.wordman.shared.data.proto.WordSyncProtoData.WordData
import io.github.manganoito.wordman.shared.data.proto.WordSyncServerServiceGrpcKt
import io.github.manganoito.wordman.shared.model.Word
import io.github.manganoito.wordman.shared.repository.WordRepository

class WordSyncServerService(
    private val wordRepository: WordRepository,
) : WordSyncServerServiceGrpcKt.WordSyncServerServiceCoroutineImplBase() {
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

private fun Word.toWordData(): WordData = WordData.newBuilder()
    .setValue(value)
    .setMeaning(meaning)
    .build()
