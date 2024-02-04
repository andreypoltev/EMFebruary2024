package com.andreypoltev.emfebruary2024.presentation.item_details_screen.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.andreypoltev.emfebruary2024.R
import com.andreypoltev.emfebruary2024.data.model.Price
import com.andreypoltev.emfebruary2024.presentation.common.DiscountTag
import com.andreypoltev.emfebruary2024.presentation.common.StrikePrice

@Composable
fun PriceRow(price: Price) {

    Row(verticalAlignment = Alignment.CenterVertically) {

        Text(
            text = "${price.priceWithDiscount} ${price.unit}",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.size(12.dp))

        StrikePrice(price = price, R.color.text_grey)
        Spacer(modifier = Modifier.size(12.dp))

        DiscountTag(discount = price.discount)


    }


}