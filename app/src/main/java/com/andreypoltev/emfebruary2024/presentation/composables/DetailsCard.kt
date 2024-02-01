package com.andreypoltev.emfebruary2024.presentation.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsCard(icon: ImageVector, title: String, text: String?, onClick: () -> Unit = {}) {


    Card(onClick = onClick, modifier = Modifier
        .fillMaxWidth()
        .onGloballyPositioned {
//                             Log.d("", it.size.height.toString())

        }, shape = RoundedCornerShape(8.dp)
    ) {
        Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = icon, contentDescription = "Icon")
            Spacer(modifier = Modifier.size(16.dp))
            Column {
                Text(text = title)


                if (text != null) {

                    Text(text = text)
                }

            }
        }
    }

}