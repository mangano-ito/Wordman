package io.github.manganoito.wordman.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.manganoito.wordman.shared.model.Word
import io.github.manganoito.wordman.ui.theme.WordManPreviewTheme

@Composable
fun WordCardList(
    words: List<Word>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(
        horizontal = 16.dp,
    ),
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(words) { word ->
            WordCard(
                word = word.value,
                meaning = word.meaning,
            )
        }
    }
}

@Preview
@Composable
private fun WordCardListPreview() = WordManPreviewTheme {
    WordCardList(
        words = listOf(
            Word(
                id = 1,
                value = "Word",
                meaning = "語，単語",
            ),
            Word(
                id = 2,
                value = "Phrase",
                meaning = "成句，熟語，慣用句，決まり文句",
            ),
        ),
    )
}
