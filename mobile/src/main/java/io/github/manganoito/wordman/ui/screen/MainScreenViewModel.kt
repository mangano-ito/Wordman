package io.github.manganoito.wordman.ui.screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.manganoito.wordman.shared.model.Word
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    // TODO
) : ViewModel() {
    var words: MutableState<List<Word>> = mutableStateOf(emptyList())
        private set
}
