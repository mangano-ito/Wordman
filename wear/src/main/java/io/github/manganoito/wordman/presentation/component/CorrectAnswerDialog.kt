package io.github.manganoito.wordman.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.dialog.Confirmation
import androidx.wear.compose.material.dialog.Dialog
import io.github.manganoito.wordman.presentation.theme.WordManPreviewTheme

@Composable
fun CorrectAnswerDialog(
    onFinish: () -> Unit,
) {
    var showDialog by rememberSaveable { mutableStateOf(true) }
    Dialog(
        showDialog = showDialog,
        onDismissRequest = {
            showDialog = false
            onFinish()
        },
    ) {
        Confirmation(
            onTimeout = {
                showDialog = false
                onFinish()
            },
        ) {
            CorrectAnswerNotice()
        }
    }
}

@Composable
fun CorrectAnswerNotice(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Icon(
            imageVector = Icons.Default.ThumbUp,
            contentDescription = null,
            modifier = Modifier.size(48.dp),
            tint = Color.Cyan,
        )
        Text(
            text = "Correct! Keep it up!",
            textAlign = TextAlign.Center,
        )
    }
}

@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
private fun CorrectAnswerNoticePreview() = WordManPreviewTheme {
    CorrectAnswerNotice()
}
