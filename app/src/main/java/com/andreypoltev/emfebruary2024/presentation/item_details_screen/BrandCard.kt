package com.andreypoltev.emfebruary2024.presentation.item_details_screen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.andreypoltev.emfebruary2024.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BrandCard(title: String) {

    Card(
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.background_light_grey)),
        onClick = {},
        modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(8.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.text_black),
                text = title,
                modifier = Modifier.padding(start = 9.dp, top = 15.dp, bottom = 15.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Brand info",
                modifier = Modifier
                    .size(32.dp)
                    .padding(end = 8.dp, top = 8.dp, bottom = 8.dp)
            )
        }

    }

}