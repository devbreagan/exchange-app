package com.gbreagan.challenge.exchange.ui.component

import android.widget.TextView
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gbreagan.challenge.exchange.ui.theme.BankStyles

@Composable
fun BankCardPlus(
    modifier: Modifier = Modifier,
    label: String,
    startPrimaryText: String = "",
    startSecondaryText: String = "",
    midPrimaryText: String = "",
    midSecondaryText: String = "",
    endPrimaryText: String = "",
    endSecondaryText: String = "",
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = label,
                    style = BankStyles.textBodyMedium()
                )
            }
            Spacer(modifier = Modifier.padding(bottom = 4.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = startPrimaryText,
                        style = BankStyles.textBodyMedium(),
                        color = BankStyles.colorNeutral
                    )
                    Text(
                        text = startSecondaryText,
                        style = BankStyles.textBodyMedium()
                    )
                }
                Spacer(modifier = Modifier.padding(start = 16.dp))
                Column {
                    Text(
                        text = midPrimaryText,
                        style = BankStyles.textBodyMedium()
                    )
                    Text(
                        text = midSecondaryText,
                        style = BankStyles.textBodyMedium()
                    )
                }
                Spacer(modifier = Modifier.padding(start = 16.dp))
                Column {
                    Text(
                        text = endPrimaryText,
                        style = BankStyles.textBodyMedium()
                    )
                    Text(
                        text = endSecondaryText,
                        style = BankStyles.textBodyMedium()
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun BankCardPlusPreview() {
    BankCardPlus(
        label = "10102022",
        startPrimaryText = "Envía:",
        startSecondaryText = "Recibe:",
        midPrimaryText = "USD",
        midSecondaryText = "PEN",
        endPrimaryText = "1000.00",
        endSecondaryText = "3650.00",
    )
}