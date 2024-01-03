package io.github.manganoito.wordman.shared.data

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException
import io.github.manganoito.wordman.shared.data.proto.WordSyncProtoData.WordCountData
import java.io.InputStream
import java.io.OutputStream

object WordCountDataSerializer : Serializer<WordCountData> {
    override val defaultValue: WordCountData
        get() = WordCountData.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): WordCountData {
        try {
            return WordCountData.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: WordCountData, output: OutputStream) {
        t.writeTo(output)
    }
}
