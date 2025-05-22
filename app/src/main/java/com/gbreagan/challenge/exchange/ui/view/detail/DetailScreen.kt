package com.gbreagan.challenge.exchange.ui.view.detail

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.gbreagan.challenge.exchange.R
import com.gbreagan.challenge.exchange.ui.component.BankCardPlus
import com.gbreagan.challenge.exchange.ui.component.BankErrorPage
import com.gbreagan.challenge.exchange.ui.component.BankLoader
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    onBackPressed: () -> Unit
) {
    val viewModel: DetailViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        modifier = Modifier.testTag("HomeScreen"),
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.title_detail)) },
                navigationIcon = {
                    IconButton(
                        modifier = Modifier.testTag("toDetail"),
                        onClick = { onBackPressed() },
                        content = {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = stringResource(id = R.string.action_back)
                            )
                        }
                    )
                }
            )
        }
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            when {
                uiState.isLoading -> {
                    BankLoader()
                }
                uiState.operations.isNotEmpty() -> {
                    val operations = uiState.operations
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp),
                        contentPadding = PaddingValues(vertical = 4.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        itemsIndexed(operations) { _, item ->
                            BankCardPlus(
                                label = item.timestamp.toString(),
                                startPrimaryText = "Envía:",
                                startSecondaryText = "Recibe:",
                                midPrimaryText = item.from,
                                midSecondaryText = item.to,
                                endPrimaryText = item.amountSend.toString(),
                                endSecondaryText = item.amountReceive.toString(),
                            )
                        }
                    }
                }
                uiState.error != null -> {
                    BankErrorPage()
                }
            }
        }
    }
}