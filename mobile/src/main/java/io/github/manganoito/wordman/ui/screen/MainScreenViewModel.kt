package io.github.manganoito.wordman.ui.screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import io.github.manganoito.wordman.shared.model.Word
import javax.inject.Inject

class MainScreenViewModel @Inject constructor(
    // TODO
) : ViewModel() {
    var words: MutableState<List<Word>> = mutableStateOf(emptyList())
        private set
}
