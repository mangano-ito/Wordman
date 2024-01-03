package io.github.manganoito.wordman.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.wearable.Node
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.data.WearDataLayerRegistry
import com.google.android.horologist.datalayer.watch.WearDataLayerAppHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@OptIn(ExperimentalHorologistApi::class)
@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val wearDataLayerAppHelper: WearDataLayerAppHelper,
    private val registry: WearDataLayerRegistry,
) : ViewModel() {
    val nodes = flow {
        val self = registry.nodeClient.localNode.await()
        emit(
            registry.nodeClient.connectedNodes.await() + self,
        )
    }.shareIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        replay = 1,
    )

    val state = nodes.map {
        MainScreenUiState(
            nodes = it,
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = MainScreenUiState(),
    )
}

data class MainScreenUiState(
    val nodes: List<Node> = emptyList(),
)
