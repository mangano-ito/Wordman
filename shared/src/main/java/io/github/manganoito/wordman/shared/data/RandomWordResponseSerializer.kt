package io.github.manganoito.wordman.shared.data

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException
import io.github.manganoito.wordman.shared.data.proto.WordSyncProtoData.RandomWordResponse
import java.io.InputStream
import java.io.OutputStream

object RandomWordResponseSerializer : Serializer<RandomWordResponse> {
    override val defaultValue: RandomWordResponse
        get() = RandomWordResponse.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): RandomWordResponse {
        try {
            return RandomWordResponse.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: RandomWordResponse, output: OutputStream) {
        t.writeTo(output)
    }
}
