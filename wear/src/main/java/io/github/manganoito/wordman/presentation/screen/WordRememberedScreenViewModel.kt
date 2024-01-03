package io.github.manganoito.wordman.presentation.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.protobuf.Empty
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.manganoito.wordman.shared.data.proto.WordSyncServerServiceGrpcKt
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordRememberedScreenViewModel @Inject constructor(
    private val wordSyncServerService: WordSyncServerServiceGrpcKt.WordSyncServerServiceCoroutineStub,
) : ViewModel() {
    var state: WordRememberedScreenUiState by mutableStateOf(WordRememberedScreenUiState.Loading)
        private set

    init {
        load()
    }

    private fun load() {
        viewModelScope.launch {
            val wordCount = wordSyncServerService.getWordCount(Empty.newBuilder().build()).count
            state = WordRememberedScreenUiState.Loaded(wordCount)
        }
    }
}
