package io.github.manganoito.wordman.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.manganoito.wordman.ui.theme.WordManTheme

@Composable
internal fun AddWordScreen(
    viewModel: AddWordScreenViewModel,
    onBack: () -> Unit,
) {
    AddWordScreen(
        onBack = onBack,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddWordScreen(
    onBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Add Word") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Go Back",
                        )
                    }
                },
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
        AddWordScreen(
            onBack = {},
        )
    }
}
