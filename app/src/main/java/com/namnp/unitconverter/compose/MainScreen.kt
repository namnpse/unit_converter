package com.namnp.unitconverter.compose

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.namnp.unitconverter.ConverterViewModel
import com.namnp.unitconverter.ConverterViewModelFactory
import com.namnp.unitconverter.compose.converter.TopScreen
import com.namnp.unitconverter.compose.history.HistoryScreen

@Composable
fun MainScreen(
    factory: ConverterViewModelFactory,
    modifier: Modifier = Modifier,
    converterViewModel: ConverterViewModel = viewModel(factory = factory)
) {

    val list = converterViewModel.getConversions()
    val historyList = converterViewModel.resultList.collectAsState(initial = emptyList())

    val configuration = LocalConfiguration.current
    var isLandscape by remember { mutableStateOf(false) }

    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            isLandscape = true
            Row(
                modifier = modifier
                    .padding(30.dp)
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                TopScreen(
                    list,
                    converterViewModel.selectedConversion,
                    converterViewModel.inputText,
                    converterViewModel.typedValue,
                    isLandscape
                ) { message1, message2 ->
                    converterViewModel.addResult(message1, message2)
                }
                HistoryScreen(
                    historyList,
                    onCloseTask = { item ->
                        converterViewModel.removeResult(item)
                    },
                    onClearAllTask = {
                        converterViewModel.clearAll()
                    }
                )
            }

        }

        else -> {
            isLandscape = false
            Column(modifier = modifier.padding(30.dp)) {
                TopScreen(
                    list,
                    converterViewModel.selectedConversion,
                    converterViewModel.inputText,
                    converterViewModel.typedValue,
                    isLandscape
                ) { message1, message2 ->
                    converterViewModel.addResult(message1, message2)
                }
                HistoryScreen(
                    historyList,
                    onCloseTask = { item ->
                        converterViewModel.removeResult(item)
                    },
                    onClearAllTask = {
                        converterViewModel.clearAll()
                    }
                )
            }
        }
    }


}