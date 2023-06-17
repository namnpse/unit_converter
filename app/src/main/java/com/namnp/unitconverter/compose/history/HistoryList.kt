package com.namnp.unitconverter.compose.history

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import com.namnp.unitconverter.data.model.ConversionResult

@Composable
fun HistoryList(
    list: State<List<ConversionResult>>,
) {
    LazyColumn {
        items(
            items = list.value,
            key = { item -> item.id }
        ) { item ->
            HistoryItem(
                messagePart1 = item.messagePart1,
                messagePart2 = item.messagePart2,
            )
        }
    }
}