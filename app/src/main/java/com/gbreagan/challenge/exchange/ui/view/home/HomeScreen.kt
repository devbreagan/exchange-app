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
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gbreagan.challenge.exchange.R
import com.gbreagan.challenge.exchange.ui.component.BankButton
import com.gbreagan.challenge.exchange.ui.theme.ExchangeTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    var amountToSend by remember { mutableStateOf("") }
    var amountToReceive by remember { mutableStateOf("") }
    var selectedCurrency by remember { mutableStateOf("Soles") }
    val exchangeRateBuy = 3.5
    val exchangeRateSell = 3.6

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.illustration_moneybag),
            contentDescription = "Logo",
            modifier = Modifier
                .size(96.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                TextField(
                    value = amountToSend,
                    onValueChange = {
                        amountToSend = it
                    },
                    label = { Text(stringResource(id = R.string.amount_to_send)) },
                )
            }
            Column {
//                Button(
//                    onClick = {},
//                    modifier = Modifier
//                        .width(100.dp)
//                        .height(56.dp),
//                    shape = RoundedCornerShape(0.dp)
//                ) {
//                    Text("PEN")
//                }

                BankButton(
                    onLongClick = { selectedCurrency = if (selectedCurrency == "Soles") "Dollars" else "Soles" },
                    modifier = Modifier
                        .width(100.dp)
                        .height(56.dp),
                ) {
                    Text("PEN", color = Color.White)
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                TextField(
                    value = amountToReceive,
                    onValueChange = { amountToReceive = it },
                    label = { Text(stringResource(id = R.string.amount_to_receive)) },
                    enabled = false
                )
            }
            Column {
                BankButton(
                    onLongClick = {},
                    modifier = Modifier
                        .width(100.dp)
                        .height(56.dp)
                ) {
                    Text("USD", color = Color.White)
                }
            }
        }

        Row {
            Text(
                stringResource(id = R.string.buy_price) + exchangeRateBuy + " | ",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                stringResource(id = R.string.sell_price) + exchangeRateSell,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { selectedCurrency = if (selectedCurrency == "Soles") "Dollars" else "Soles" },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(0.dp)
        ) {
            Text(stringResource(id = R.string.start_operation))
        }
    }
}
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    ExchangeTheme(dynamicColor = false) {
        HomeScreen()
    }
}