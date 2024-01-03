package io.github.manganoito.wordman.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.manganoito.wordman.shared.repository.WordRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddWordScreenViewModel @Inject constructor(
    private val wordRepository: WordRepository,
) : ViewModel() {
    fun saveWord(
        word: String,
        meaning: String,
    ) {
        viewModelScope.launch {
            wordRepository.addWord(
                word = word,
                meaning = meaning,
            )
        }
    }
}
