package com.andreypoltev.emfebruary2024.presentation.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.andreypoltev.emfebruary2024.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsCard(
    leadingIcon: Int,
    trailingIcon: Int,
    tint: Int = R.color.black,
    tintTrailing: Int = R.color.black,
    title: String,
    text: String?,
    onClick: () -> Unit = {}
) {


    Card(
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.background_light_grey)),
        onClick = onClick, modifier = Modifier
            .fillMaxWidth()
            .onGloballyPositioned {
//                             Log.d("", it.size.height.toString())

            }, shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(24.dp),
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    painter = painterResource(id = leadingIcon),
                    contentDescription = "Icon",
                    tint = colorResource(
                        id = tint
                    )
                )
            }
            Spacer(modifier = Modifier.size(16.dp))
            Column {
                Text(
                    text = title,
                    color = colorResource(id = R.color.text_black),
                    fontWeight = FontWeight.SemiBold
                )


                if (text != null) {

                    Text(text = text, color = colorResource(id = R.color.text_grey))
                }

            }

            Spacer(modifier = Modifier.weight(1f))
            Icon(
                modifier = Modifier.padding(8.dp),
                painter = painterResource(id = trailingIcon),
                contentDescription = "Icon",
                tint = colorResource(
                    id = tintTrailing
                )
            )

        }
    }

}