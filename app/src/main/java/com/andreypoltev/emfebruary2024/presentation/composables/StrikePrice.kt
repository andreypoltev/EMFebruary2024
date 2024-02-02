package com.andreypoltev.emfebruary2024.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import com.andreypoltev.emfebruary2024.R
import com.andreypoltev.emfebruary2024.data.model.Price

@Composable
fun StrikePrice(price: Price, colorResource: Int) {

    Box(contentAlignment = Alignment.Center) {
        Image(painter = painterResource(id = R.drawable.discount_strikethrough), null)
        Text(
            color = colorResource(R.color.text_grey),
            text = "${price.price} ${price.unit}",
            style = MaterialTheme.typography.titleSmall
        )
//        Text(text = "${price.price} ${price.unit}", fontSize = fontSize)
    }

}