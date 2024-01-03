package io.github.manganoito.wordman.ui.screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.data.WearDataLayerRegistry
import com.google.android.horologist.data.activityConfig
import com.google.android.horologist.datalayer.phone.PhoneDataLayerAppHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.manganoito.wordman.di.GlobalScope
import io.github.manganoito.wordman.shared.repository.WordRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@OptIn(ExperimentalHorologistApi::class)
@HiltViewModel
class AddWordScreenViewModel @Inject constructor(
    private val wordRepository: WordRepository,
    private val dataLayerAppHelper: PhoneDataLayerAppHelper,
    private val wearDataLayerRegistry: WearDataLayerRegistry,
    @GlobalScope private val globalScope: CoroutineScope,
) : ViewModel() {
    var state by mutableStateOf(AddWordScreenUiState())
        private set

    fun updateWord(value: String) {
        state = state.copy(
            word = value,
        )
    }

    fun updateMeaning(value: String) {
        state = state.copy(
            meaning = value,
        )
    }

    fun commitSave() {
        globalScope.launch {
            wordRepository.addWord(
                word = state.word,
                meaning = state.meaning,
            )
            val nodes = wearDataLayerRegistry.nodeClient.connectedNodes.await()
            val config = activityConfig {
                classFullName = "io.github.manganoito.wordman.presentation.WordRememberedActivity"
            }
            nodes.forEach {
                val result = dataLayerAppHelper.startRemoteActivity(
                    node = it.id,
                    config = config,
                )
                Log.d("WordMan", "startRemoteActivity result: $result")
            }
        }
    }
}
