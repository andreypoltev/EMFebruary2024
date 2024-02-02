package com.andreypoltev.emfebruary2024.presentation.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.andreypoltev.emfebruary2024.ImagesMapper
import com.andreypoltev.emfebruary2024.MainViewModel
import com.andreypoltev.emfebruary2024.R
import com.andreypoltev.emfebruary2024.data.model.Item


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
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
                viewModel = viewModel, item = item, modifier = Modifier
//                    .size(24.dp)
                    .align(
                        Alignment.TopEnd
                    )
                    .zIndex(1.0F)
            )


            Column(modifier = Modifier.padding(8.dp)) {


                val images = ImagesMapper.imagesMap[item.id] ?: emptyList()

                val pagerState = rememberPagerState {
                    images.size
                }


                Box(modifier = Modifier.fillMaxSize()) {


                    HorizontalPager(state = pagerState) {
                        Image(
                            painter = painterResource(id = images[it]),
                            contentDescription = null
                        )

                    }

                    val z = androidx.compose.ui.Alignment.BottomCenter

                    PageIndicator(
                        pagerState = pagerState,
                        size = 4.dp,
                        modifier = Modifier
                            .wrapContentHeight()
                            .align(Alignment.BottomCenter)
                    )


                }




                StrikePrice(item.price, R.color.text_grey)

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "${item.price.priceWithDiscount} ${item.price.unit}",
                        style = MaterialTheme.typography.titleLarge
                    )

                    Spacer(modifier = Modifier.size(8.dp))

                    DiscountTag(item.price.discount)
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