package io.github.manganoito.wordman.presentation.screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.protobuf.Empty
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.manganoito.wordman.shared.data.proto.WordSyncServerServiceGrpcKt
import io.github.manganoito.wordman.shared.model.Word
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordCheckScreenViewModel @Inject constructor(
    private val wordSyncServerService: WordSyncServerServiceGrpcKt.WordSyncServerServiceCoroutineStub,
) : ViewModel() {
    var state: WordCheckScreenUiState by mutableStateOf(WordCheckScreenUiState.Loading)
        private set

    init {
        load()
    }

    private fun load() {
        viewModelScope.launch {
            state = WordCheckScreenUiState.Loaded(
                words = listOf(
                    Word(
                        id = 0,
                        value = "Hello",
                        meaning = "こんにちは",
                    ),
                    Word(
                        id = 1,
                        value = "Goodbye",
                        meaning = "さようなら",
                    ),
                    Word(
                        id = 2,
                        value = "Hi",
                        meaning = "やあ",
                    ),
                ),
            )
        }
    }

    fun doSync() {
        viewModelScope.launch {
            try {
                val result = wordSyncServerService.getRandomWord(Empty.newBuilder().build())
                Log.d("WordSyncService", "Result: $result")
            } catch (e: Throwable) {
                Log.e("WordSyncService", "Error", e)
            }
        }
    }
}
