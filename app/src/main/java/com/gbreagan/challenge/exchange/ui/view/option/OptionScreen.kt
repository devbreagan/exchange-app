package com.gbreagan.challenge.exchange.ui.view.option

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.gbreagan.challenge.exchange.R
import com.gbreagan.challenge.exchange.ui.component.BankCard
import com.gbreagan.challenge.exchange.ui.component.BankErrorPage
import com.gbreagan.challenge.exchange.ui.component.BankLoader
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OptionScreen(
    source: String,
    onItemSelected: (String, String) -> Unit,
    onBackPressed: () -> Unit
) {
    val viewModel : OptionViewModel = koinViewModel()
    val uiState by viewModel.uiState//.collectAsState()

    LaunchedEffect(Unit) { viewModel.loadOptions() }

    Scaffold(
        modifier = Modifier.testTag("OptionScreen"),
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        modifier = Modifier.testTag("toBack"),
                        onClick = { onBackPressed() },
                        content = {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = stringResource(id = R.string.action_back)
                            )
                        }
                    )
                },
                title = { Text(stringResource(id = R.string.title_option)) },
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
        ) {
            when  {
                uiState.isLoading -> {
                    BankLoader()
                }
                uiState.error != null -> {
                    BankErrorPage()
                }
                uiState.options.isNotEmpty() -> {
                    val rates = uiState.options
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp),
                        contentPadding = PaddingValues(vertical = 4.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        itemsIndexed(rates) { _, item ->
                            BankCard(
                                label = item.name,
                                primaryText = "${item.code} = ${item.rate}",
                            ) {
                                onItemSelected(source, item.code)
                            }
                        }
                    }
                }
            }
        }
    }
}