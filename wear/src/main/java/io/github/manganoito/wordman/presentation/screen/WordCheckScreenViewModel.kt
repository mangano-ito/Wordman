package io.github.manganoito.wordman.presentation.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.protobuf.Empty
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.manganoito.wordman.shared.data.proto.WordSyncServiceGrpcKt
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordCheckScreenViewModel @Inject constructor(
    private val wordSyncClient: WordSyncServiceGrpcKt.WordSyncServiceCoroutineStub,
) : ViewModel() {
    fun doSync() {
        viewModelScope.launch {
            try {
                val result = wordSyncClient.getRandomWord(Empty.newBuilder().build())
                Log.d("WordSyncService", "Result: $result")
            } catch (e: Throwable) {
                Log.e("WordSyncService", "Error", e)
            }
        }
    }
}
