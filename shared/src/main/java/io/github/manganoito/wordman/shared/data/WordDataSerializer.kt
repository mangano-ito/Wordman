package io.github.manganoito.wordman.shared.data

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException
import io.github.manganoito.wordman.shared.data.proto.WordSyncProtoData.WordData
import java.io.InputStream
import java.io.OutputStream

object WordDataSerializer : Serializer<WordData> {
    override val defaultValue: WordData
        get() = WordData.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): WordData {
        try {
            return WordData.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: WordData, output: OutputStream) {
        t.writeTo(output)
    }
}
