package io.github.manganoito.wordman.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.wear.compose.material.MaterialTheme
import dagger.hilt.android.AndroidEntryPoint
import io.github.manganoito.wordman.presentation.screen.WordRememberedScreen
import io.github.manganoito.wordman.presentation.theme.WordManTheme

@AndroidEntryPoint
class WordRememberedActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WordManTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colors.background),
                    contentAlignment = Alignment.Center,
                ) {
                    WordRememberedScreen(
                        viewModel = hiltViewModel(),
                        onFinish = { finish() },
                    )
                }
            }
        }
    }
}
