package io.github.manganoito.wordman.shared.data

import android.content.Context
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.data.WearDataLayerRegistry
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalHorologistApi::class)
fun createWearDataLayerRegistry(
    applicationContext: Context,
    coroutineScope: CoroutineScope,
): WearDataLayerRegistry {
    return WearDataLayerRegistry.fromContext(
        application = applicationContext,
        coroutineScope = coroutineScope,
    ).apply {
        registerSerializer(WordDataSerializer)
    }
}
