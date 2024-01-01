package io.github.manganoito.wordman.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.manganoito.wordman.ui.theme.WordManTheme

@Composable
internal fun AddWordScreen(
    viewModel: AddWordScreenViewModel,
    onBack: () -> Unit,
) {
    AddWordScreen(
        state = viewModel.state,
        onBack = onBack,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddWordScreen(
    state: AddWordScreenUiState,
    onBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            AddWordScreenTopBar(onBack = onBack)
        },
    ) { paddingValues ->
        AddWordScreenContent(
            word = state.word,
            meaning = state.meaning,
            modifier = Modifier.padding(paddingValues),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddWordScreenTopBar(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = { Text(text = "Add Word") },
        modifier = modifier,
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Go Back",
                )
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddWordScreenContent(
    word: String,
    meaning: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(
            horizontal = 16.dp,
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        OutlinedTextField(
            value = word,
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Word") },
        )
        OutlinedTextField(
            value = meaning,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {},
            label = { Text(text = "Meaning") },
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.align(Alignment.End),
        ) {
            Text(text = "Add")
        }
    }
}

@Preview
@Composable
private fun AddWordScreenPreview() {
    WordManTheme {
        AddWordScreen(
            state = AddWordScreenUiState(),
            onBack = {},
        )
    }
}
