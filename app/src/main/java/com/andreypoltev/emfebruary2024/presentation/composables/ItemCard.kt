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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andreypoltev.emfebruary2024.MainViewModel
import com.andreypoltev.emfebruary2024.data.model.Item

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemCard(viewModel: MainViewModel, item: Item, onClick: () -> Unit) {

    val favorites by viewModel.flowFavorites().collectAsState(initial = emptyList())

    OutlinedCard(onClick = onClick) {
        Column() {

            Box(modifier = Modifier.fillMaxWidth()) {
                FavoritesButton(
                    viewModel = viewModel, item = item, modifier = Modifier.align(
                        Alignment.TopEnd
                    )
                )


            }

            Column(modifier = Modifier.padding(8.dp)) {


                StrikePrice(item.price, 9.sp)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "${item.price.priceWithDiscount} ${item.price.unit}",
                        style = MaterialTheme.typography.titleLarge
                    )

                    Spacer(modifier = Modifier.size(8.dp))

                    // TODO Card looks weird
                    Card(shape = RoundedCornerShape(4.dp)) {
                        Text(
                            text = "-${item.price.discount}%",
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp),
                            fontSize = 9.sp
                        ) // TODO Зачёркнуто

                    }

                }
                Text(text = item.title)
                Text(text = item.subtitle)
            }
        }


    }

}