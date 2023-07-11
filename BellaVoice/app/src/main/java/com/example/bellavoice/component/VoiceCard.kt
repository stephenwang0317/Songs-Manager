package com.example.bellavoice.component

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun VoiceCard(
    modifier: Modifier = Modifier,
    num: Int = 0
) {
    Card(
        modifier = modifier.defaultMinSize(
            minHeight = 100.dp
        )
    ) {
        Text(
            text = "Title Title Title",
            fontSize = 20.sp,
            modifier = Modifier.padding(
                top = 5.dp,
                start = 10.dp,
                end = 10.dp
            )
        )
        Text(
            text = "Description Description Description Description",
            fontSize = 15.sp,
            color = Color.DarkGray,
            modifier = Modifier.padding(
                start = 10.dp,
                end = 10.dp,
                bottom = 5.dp
            )
        )
    }
}