package com.andreypoltev.emfebruary2024.presentation.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andreypoltev.emfebruary2024.data.model.Price

@Composable
fun PriceRow(price: Price) {

    Row(verticalAlignment = Alignment.CenterVertically) {

        Text(text = "${price.priceWithDiscount} ${price.unit}")

        Spacer(modifier = Modifier.size(12.dp))

        StrikePrice(price = price, 12.sp)
        Spacer(modifier = Modifier.size(12.dp))

        // TODO Card looks weird
        Card(shape = RoundedCornerShape(4.dp)) {
            Text(text = "-${price.discount}%", modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp), fontSize = 9.sp) // TODO Зачёркнуто

        }


    }


}