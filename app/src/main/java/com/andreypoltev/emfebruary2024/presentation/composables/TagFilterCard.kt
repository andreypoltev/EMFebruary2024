package com.andreypoltev.emfebruary2024.presentation.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.andreypoltev.emfebruary2024.R

@Composable
fun TagFilterCard(resourceId: Int, onClick: () -> Unit) {

    Card(
        onClick = onClick,
        shape = RoundedCornerShape(100.dp), colors = CardDefaults.cardColors(
            containerColor = colorResource(
                id = R.color.background_light_grey
            )
        )
    ) {
        Text(
            color = colorResource(id = R.color.text_grey),
            text = stringResource(id = resourceId),
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
        )

    }

}