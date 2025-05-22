package com.gbreagan.challenge.exchange.ui.view.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.gbreagan.challenge.exchange.R
import com.gbreagan.challenge.exchange.ui.component.BankButton
import com.gbreagan.challenge.exchange.ui.theme.BankStyles
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onSelectCurrency: (String) -> Unit,
    selectedOptions: Map<String, String>,
    onDetailScreen: () -> Unit,
) {
    val viewModel: HomeViewModel = koinViewModel()
    val uiState by viewModel.uiState
    val selectedCurrencySend by remember { mutableStateOf(selectedOptions[HomeConstants.SOURCE_CURRENCY_SEND].orEmpty()) }
    val selectedCurrencyReceive by remember { mutableStateOf(selectedOptions[HomeConstants.SOURCE_CURRENCY_RECEIVE].orEmpty()) }
    Scaffold(
        modifier = Modifier.testTag("HomeScreen"),
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.title_home)) },
                actions = {
                    IconButton(
                        modifier = Modifier.testTag("toDetail"),
                        onClick = { onDetailScreen() },
                        content = {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.List,
                                contentDescription = stringResource(id = R.string.action_detail)
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
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.illustration_moneybag),
                    contentDescription = stringResource(id = R.string.app_name),
                    modifier = Modifier
                        .size(96.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Column {
                        OutlinedTextField(
                            value = uiState.amountToSend,
                            onValueChange = { viewModel.onAmountSendChange(it) },
                            label = { Text(stringResource(id = R.string.amount_to_send)) },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Decimal,
                                imeAction = ImeAction.Done
                            ),
                            singleLine = true,
                            shape = RoundedCornerShape(
                                topStart = 8.dp,
                                topEnd = 0.dp,
                                bottomStart = 8.dp,
                                bottomEnd = 0.dp
                            )
                        )
                    }
                    Column {
                        BankButton(
                            onLongClick = {
                                onSelectCurrency(HomeConstants.SOURCE_CURRENCY_SEND)
                            },
                            modifier = Modifier
                                .width(100.dp)
                                .height(56.dp),
                        ) {
                            Text(text = selectedCurrencySend, color = Color.White)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Column {
                        OutlinedTextField(
                            value = uiState.amountToReceive,
                            onValueChange = { viewModel.onAmountReceiveChange(it) },
                            label = { Text(stringResource(id = R.string.amount_to_receive)) },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                            singleLine = true,
                            readOnly = true,
                            shape = RoundedCornerShape(
                                topStart = 8.dp,
                                topEnd = 0.dp,
                                bottomStart = 8.dp,
                                bottomEnd = 0.dp
                            )
                        )
                    }
                    Column {
                        BankButton(
                            onLongClick = {
                                onSelectCurrency(HomeConstants.SOURCE_CURRENCY_RECEIVE)
                            },
                            modifier = Modifier
                                .width(100.dp)
                                .height(56.dp)
                        ) {
                            Text(text = selectedCurrencyReceive, color = Color.White)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(32.dp))
                if (uiState.error != null) {
                    Text(
                        text = uiState.error.orEmpty(),
                        color = BankStyles.colorNegative,
                        style = BankStyles.textBodyMedium()
                    )
                }
                Button(
                    onClick = {
                        viewModel.convertCurrency(selectedCurrencySend, selectedCurrencyReceive)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    enabled = uiState.amountToSend.isNotBlank(),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    if (uiState.isLoading) {
                        CircularProgressIndicator()
                    } else {
                        Text(stringResource(id = R.string.start_operation), color = Color.White)
                    }
                }
            }
        }
    }
}