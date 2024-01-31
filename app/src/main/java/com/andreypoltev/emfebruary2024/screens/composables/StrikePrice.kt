package com.andreypoltev.emfebruary2024.screens.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.TextUnit
import com.andreypoltev.emfebruary2024.R
import com.andreypoltev.emfebruary2024.data.model.Price

@Composable
fun StrikePrice(price: Price, fontSize: TextUnit) {

    Box(contentAlignment = Alignment.Center) {
        Image(painter = painterResource(id = R.drawable.discount_strikethrough), null)
        Text(text = "${price.price} ${price.unit}", fontSize = fontSize)
    }

}