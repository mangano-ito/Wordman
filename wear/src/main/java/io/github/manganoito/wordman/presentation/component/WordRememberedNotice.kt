package io.github.manganoito.wordman.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text
import io.github.manganoito.wordman.presentation.theme.WordManPreviewTheme

@Composable
fun WordRememberedNotice(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            imageVector = Icons.Default.Done,
            contentDescription = null,
            modifier = Modifier.size(48.dp),
            tint = Color.Green,
        )
        Text(
            text = "Great!\nYou’ve remembered\na new word!",
            textAlign = TextAlign.Center,
        )
    }
}

@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
private fun WordRememberedNoticePreview() = WordManPreviewTheme {
    WordRememberedNotice()
}
