package io.github.manganoito.wordman.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.manganoito.wordman.ui.theme.WordManTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AddWordScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Words") },
            )
        },
    ) { paddingValues ->
        AddWordScreenContent(
            modifier = Modifier.padding(paddingValues),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddWordScreenContent(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        TextField(
            value = "",
            onValueChange = {},
            label = { Text(text = "Word") },
        )
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Add")
        }
    }
}

@Preview
@Composable
private fun AddWordScreenPreview() {
    WordManTheme {
        AddWordScreen()
    }
}
