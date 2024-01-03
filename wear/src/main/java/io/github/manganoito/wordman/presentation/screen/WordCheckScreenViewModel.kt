package io.github.manganoito.wordman.presentation.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
            val words = wordSyncServerService.getRandomWords(
                io.github.manganoito.wordman.shared.data.proto.WordSyncProtoData.RandomWordRequest.newBuilder()
                    .setCount(3)
                    .build(),
            ).wordsList.map {
                Word(
                    id = it.id.toInt(),
                    value = it.value,
                    meaning = it.meaning,
                )
            }
            state = WordCheckScreenUiState.Loaded(
                words = words,
            )
        }
    }

    fun answer(word: Word) {
    }
}
