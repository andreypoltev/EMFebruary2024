package com.andreypoltev.emfebruary2024.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andreypoltev.emfebruary2024.MainViewModel
import com.andreypoltev.emfebruary2024.R
import com.andreypoltev.emfebruary2024.data.model.Item
import kotlin.math.roundToInt


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemCard(viewModel: MainViewModel, item: Item, onClick: () -> Unit) {

    val favorites by viewModel.flowFavorites().collectAsState(initial = emptyList())

    OutlinedCard(
        onClick = onClick, colors = CardDefaults.outlinedCardColors(
            containerColor = colorResource(
                id = R.color.background_white
            )
        )
    ) {

        Box(modifier = Modifier.fillMaxSize()) {

            FavoritesButton(
                viewModel = viewModel, item = item, modifier = Modifier.align(
                    Alignment.TopEnd
                )
            )

            Column(modifier = Modifier.padding(8.dp)) {


                StrikePrice(item.price, 9.sp)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "${item.price.priceWithDiscount} ${item.price.unit}",
                        style = MaterialTheme.typography.titleLarge
                    )

                    Spacer(modifier = Modifier.size(8.dp))

                    val radiusInDp = 4f
                    val density = LocalContext.current.resources.displayMetrics.density
                    val radiusInPixels = (radiusInDp * density).roundToInt()


                    Box(
                        modifier = Modifier
                            .clip(object : Shape {
                                override fun createOutline(
                                    size: Size,
                                    layoutDirection: LayoutDirection,
                                    density: Density
                                ): Outline {
                                    return Outline.Rounded(
                                        roundRect = RoundRect(
                                            left = 0f,
                                            top = size.height / 8,
                                            right = size.width,
                                            bottom = size.height * 7 / 8,
                                            radiusX = radiusInPixels.toFloat(), // Adjust the radius as needed
                                            radiusY = radiusInPixels.toFloat()  // Adjust the radius as needed
                                        )
                                    )

                                }
                            })
                            .background(colorResource(id = R.color.background_pink))
                    ) {


                        Text(
                            modifier = Modifier.padding(horizontal = 6.dp),
                            text = "-${item.price.discount}%",
                            fontSize = 9.sp,
                            color = colorResource(id = R.color.text_white)
//                            style = TextStyle(background = colorResource(id = R.color.background_pink))
                        )
                    }


//                    // TODO Card looks weird
//                    Card(shape = RoundedCornerShape(4.dp), modifier = Modifier.height(16.dp)) {
//                        Column() {
//
//                        }
//
//                    }

                }
                Text(text = item.title)
                Text(text = item.subtitle)

                Spacer(modifier = Modifier.size(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            painter = painterResource(id = R.drawable.star),
                            contentDescription = null,
                            Modifier.size(16.dp),
                            tint = colorResource(id = R.color.element_orange)
                        )
                    }

                    Spacer(modifier = Modifier.size(4.dp))
                    Text(
                        text = item.feedback?.rating.toString(),
                        color = colorResource(id = R.color.text_orange),
                    )

                    Spacer(modifier = Modifier.size(4.dp))

                    Text(
                        text = "(${item.feedback?.count.toString()})",
                        color = colorResource(id = R.color.text_grey)
                    )

                }



                Spacer(modifier = Modifier.size(20.dp))
            }
            Card(
                onClick = {

                },
                modifier = Modifier
                    .size(32.dp)
                    .align(Alignment.BottomEnd),
                shape = RoundedCornerShape(topStart = 8.dp, bottomEnd = 8.dp)
            ) {

                AddToCartButton()
            }


        }


    }

}