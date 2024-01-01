package io.github.manganoito.wordman.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.manganoito.wordman.shared.repository.WordRepository
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val wordRepository: WordRepository,
) : ViewModel() {
    var state by mutableStateOf(MainScreenUiState())
        private set

    init {
        wordRepository.observeAllWords()
            .onEach {
                state = state.copy(
                    words = it,
                )
            }
            .launchIn(viewModelScope)
    }
}
