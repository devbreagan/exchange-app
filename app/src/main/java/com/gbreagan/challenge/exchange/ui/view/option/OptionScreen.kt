package com.gbreagan.challenge.exchange.ui.view.option

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.gbreagan.challenge.exchange.R
import com.gbreagan.challenge.exchange.core.result.ResultData
import com.gbreagan.challenge.exchange.ui.component.BankCard
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OptionScreen(
    source: String,
    onItemSelected: (String, String) -> Unit,
    onBackClick: () -> Unit
) {
    val viewModel : OptionViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        modifier = Modifier.testTag("OptionScreen"),
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        modifier = Modifier.testTag("toBack"),
                        onClick = { onBackClick() },
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
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            when (uiState) {
                is ResultData.Loading -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                is ResultData.Success -> {
                    val rates = (uiState as ResultData.Success).data
                    LazyColumn {
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
                is ResultData.Failure -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text("Error al cargar datos")
                    }
                }
            }
        }
    }
}