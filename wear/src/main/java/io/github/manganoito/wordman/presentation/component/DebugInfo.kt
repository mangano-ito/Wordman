package io.github.manganoito.wordman.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.wear.compose.material.Text
import com.google.android.gms.wearable.Node

@Composable
fun DebugInfo(
    nodes: List<Node>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        Text(text = "Debug Info")
        nodes.forEach {
            Text(text = "${it.displayName}(${it.id}) ${if (it.isNearby) "NEAR" else ""}")
        }
    }
}
