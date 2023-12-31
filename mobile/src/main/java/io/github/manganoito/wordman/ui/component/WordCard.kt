package io.github.manganoito.wordman.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.manganoito.wordman.ui.theme.WordManPreviewTheme

@Composable
fun WordCard(
    word: String,
    meaning: String,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
        ) {
            Text(
                text = word,
                style = MaterialTheme.typography.displaySmall,
            )
            Text(
                text = meaning,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

@Preview
@Composable
private fun WordCardPreview() = WordManPreviewTheme {
    WordCard(
        word = "Word",
        meaning = "語，単語",
    )
}
