package io.github.manganoito.wordman.shared.data

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException
import io.github.manganoito.wordman.shared.data.proto.WordSyncProtoData.WordSyncData
import java.io.InputStream
import java.io.OutputStream

object WordSyncDataSerializer : Serializer<WordSyncData> {
    override val defaultValue: WordSyncData
        get() = WordSyncData.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): WordSyncData {
        try {
            return WordSyncData.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: WordSyncData, output: OutputStream) {
        t.writeTo(output)
    }
}
