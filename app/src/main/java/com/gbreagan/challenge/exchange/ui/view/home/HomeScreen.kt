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
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gbreagan.challenge.exchange.R
import com.gbreagan.challenge.exchange.data.orZero
import com.gbreagan.challenge.exchange.ui.component.BankButton
import com.gbreagan.challenge.exchange.ui.theme.ExchangeTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onSelectCurrency: (String) -> Unit,
    selectedOptions: Map<String, String>
) {
    val viewModel: HomeViewModel = koinViewModel()
    val uiState by viewModel.uiState
    var amountToReceive by remember { mutableStateOf("${uiState.amountToReceive.orZero()}") }
    val selectedCurrencySend by remember { mutableStateOf(selectedOptions[HomeConstants.SOURCE_CURRENCY_SEND].orEmpty()) }
    val selectedCurrencyReceive by remember { mutableStateOf(selectedOptions[HomeConstants.SOURCE_CURRENCY_RECEIVE].orEmpty()) }

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
                    value = uiState.amountToSend.orZero().toString(),
                    onValueChange = {viewModel.onAmountSendChange(it) },
                    label = { Text(stringResource(id = R.string.amount_to_send)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    singleLine = true,
                    shape = RoundedCornerShape(topStart = 8.dp, topEnd = 0.dp, bottomStart = 8.dp, bottomEnd = 0.dp)
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
                    value = uiState.amountToReceive.toString(),
                    onValueChange = { amountToReceive = it },
                    label = { Text(stringResource(id = R.string.amount_to_send)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    singleLine = true,
                    readOnly = true,
                    shape = RoundedCornerShape(topStart = 8.dp, topEnd = 0.dp, bottomStart = 8.dp, bottomEnd = 0.dp)
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
        Button(
            onClick = {
                viewModel.convertCurrency(selectedCurrencySend, selectedCurrencyReceive)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(stringResource(id = R.string.start_operation), color = Color.White)
        }
    }
}
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    ExchangeTheme(dynamicColor = false) {
//        HomeScreen(
//            onSelectCurrency = {}
//        )
    }
}