package com.andreypoltev.emfebruary2024.presentation.item_details_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.andreypoltev.emfebruary2024.MainViewModel
import com.andreypoltev.emfebruary2024.R
import com.andreypoltev.emfebruary2024.presentation.common.CustomProgressIndicator
import com.andreypoltev.emfebruary2024.presentation.common.FavoritesButton
import com.andreypoltev.emfebruary2024.presentation.common.PageIndicator
import com.andreypoltev.emfebruary2024.presentation.common.StrikePrice
import com.andreypoltev.emfebruary2024.presentation.item_details_screen.composables.Details
import com.andreypoltev.emfebruary2024.presentation.item_details_screen.composables.PriceRow
import com.andreypoltev.emfebruary2024.util.ImagesMapper

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ItemDetailsScreen(viewModel: MainViewModel, navController: NavHostController, id: String?) {

    val items by viewModel.items.collectAsState()

    val item = items.find { it.id == id.toString() }

    if (item != null) {

        Column(modifier = Modifier.fillMaxWidth()) {


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    FavoritesButton(
                        viewModel = viewModel, item = item, modifier = Modifier
//                    .size(24.dp)
                            .align(
                                Alignment.TopEnd
                            )
                            .zIndex(1.0F)
                    )


                    val images = ImagesMapper.imagesMap[item.id] ?: emptyList()

                    val pagerState = rememberPagerState {
                        images.size
                    }

                    CustomHorizontalPager(images, pagerState)

                    Box(
                        modifier = Modifier
                            .padding(bottom = 16.dp)
                            .align(Alignment.BottomStart),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier

                                .size(16.dp)
                                .clip(CircleShape)
                                .clickable { }
                                .background(
                                    colorResource(
                                        id = R.color.element_light_grey
                                    ).copy(alpha = 0.35f)
                                ), contentAlignment = Alignment.Center
                        ) {

                            Icon(
                                modifier = Modifier.size(12.dp),
                                painter = painterResource(id = R.drawable.question_mark_icon),
                                contentDescription = null,
                                tint = colorResource(id = R.color.element_white)
                            )

                        }
                    }




                    PageIndicator(
                        pagerState = pagerState,
                        size = 6.dp,
                        modifier = Modifier
                            .wrapContentHeight()
                            .align(Alignment.BottomCenter)
                    )


                }

//                Text(text = item.id)
                Text(
                    text = item.title,
                    fontWeight = FontWeight.SemiBold,
                    color = colorResource(id = R.color.text_grey)
                )
                Text(
                    text = item.subtitle,
                    style = MaterialTheme.typography.titleLarge
                )


                // TODO "Отображаемое значение формируется так: “Доступно для заказа {available} штук”. В зависимости от значения available, последнее слово склоняется: “штука”, “штуки”, “штуки”."
                Text(
                    text = "${stringResource(id = R.string.in_stock)}: ${item.available}",
                    color = colorResource(
                        id = R.color.text_grey
                    )
                )

                Spacer(modifier = Modifier.size(10.dp))

                HorizontalDivider()

                Spacer(modifier = Modifier.size(10.dp))


                if (item.feedback != null) {
                    FeedbackRow(item.feedback)
                }

                PriceRow(item.price)

                Spacer(modifier = Modifier.size(24.dp))

                var visible by remember { mutableStateOf(true) }

                Text(
                    text = stringResource(id = R.string.description),
                    style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.SemiBold
                )

                AnimatedVisibility(visible = visible) {
                    Column {

                        Spacer(modifier = Modifier.size(16.dp))
                        BrandCard(item.title)
                        Spacer(modifier = Modifier.size(8.dp))

                        Text(text = item.description)

                        Spacer(modifier = Modifier.size(10.dp))
                    }
                }

                Text(
                    text = if (!visible) stringResource(id = R.string.show_description) else stringResource(
                        id = R.string.hide_description
                    ), modifier = Modifier.clickable {
                        visible = !visible
                    }
                )
                Spacer(modifier = Modifier.size(34.dp))

                Details(item.info)

                Spacer(modifier = Modifier.size(34.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {

                    Text(
                        text = stringResource(id = R.string.ingridients),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    // TODO needs copy icon
                    IconButton(onClick = { }) {
                        Icon(
                            modifier = Modifier.size(32.dp),
                            painter = painterResource(id = R.drawable.copy_icon),
                            contentDescription = "Copy",
                            tint = colorResource(
                                id = R.color.text_grey
                            )
                        )

                    }

                }



                Spacer(modifier = Modifier.size(16.dp))

//                Text(text = item.ingredients, maxLines = 2)

                var ingridientsAreVisible by remember { mutableStateOf(false) }

                var hasVisualOverflow by remember { mutableStateOf(false) }

                Text(
                    text = "${item.ingredients}${item.ingredients}",
                    maxLines = if (ingridientsAreVisible) Int.MAX_VALUE else 2,
                    overflow = TextOverflow.Ellipsis, onTextLayout = { textLayoutResult ->
                        hasVisualOverflow = textLayoutResult.hasVisualOverflow

                    }
                )

                // TODO условия туплю
                Text(
                    text = if (!ingridientsAreVisible) stringResource(id = R.string.show_description) else stringResource(
                        id = R.string.hide_description
                    ), modifier = Modifier.clickable {
                        ingridientsAreVisible = !ingridientsAreVisible
                    }
                )

                Spacer(modifier = Modifier.size(32.dp))

            }


            Card(
                colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.background_pink)),
                onClick = {

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
//                .align(Alignment.BottomCenter), shape = RoundedCornerShape(8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        style = MaterialTheme.typography.titleMedium,
                        color = colorResource(id = R.color.text_white),
                        text = item.price.priceWithDiscount.toString() + item.price.unit,
                        modifier = Modifier.padding(top = 16.dp, bottom = 16.dp, start = 16.dp)
                    )

                    Spacer(modifier = Modifier.size(8.dp))
                    StrikePrice(price = item.price, R.color.text_light_pink)


                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        color = colorResource(id = R.color.text_white),
                        fontWeight = FontWeight.SemiBold,
                        text = stringResource(id = R.string.add_to_cart),
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }


        }
    } else {
        CustomProgressIndicator()
    }
}


