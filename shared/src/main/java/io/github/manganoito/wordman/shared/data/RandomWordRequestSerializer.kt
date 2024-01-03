package io.github.manganoito.wordman.shared.data

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException
import io.github.manganoito.wordman.shared.data.proto.WordSyncProtoData.RandomWordRequest
import java.io.InputStream
import java.io.OutputStream

object RandomWordRequestSerializer : Serializer<RandomWordRequest> {
    override val defaultValue: RandomWordRequest
        get() = RandomWordRequest.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): RandomWordRequest {
        try {
            return RandomWordRequest.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: RandomWordRequest, output: OutputStream) {
        t.writeTo(output)
    }
}
