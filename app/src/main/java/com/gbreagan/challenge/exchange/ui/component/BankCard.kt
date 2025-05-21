package com.gbreagan.challenge.exchange.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gbreagan.challenge.exchange.R
import com.gbreagan.challenge.exchange.ui.theme.BankStyles

@Composable
fun BankCard(
    modifier: Modifier = Modifier,
    label: String,
    primaryText: String = "",
    @DrawableRes image: Int? = R.drawable.ic_launcher_foreground,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            image?.let {
                Image(
                    painter = painterResource(id = image),
                    contentDescription = label,
                    modifier = Modifier.size(48.dp)
                )
            }
            Spacer(modifier = Modifier.padding(start = 16.dp))
            Column {
                Text(
                    text = label,
                    style = BankStyles.textBodyLarge()
                )
                Text(
                    text = primaryText,
                    style = BankStyles.textTitleMedium()
                )
            }
        }
    }
}

@Preview
@Composable
fun BankCardPreview() {
    BankCard(
        label = "Perú",
        primaryText = "PEN = 3.65213",
    ) {}
}