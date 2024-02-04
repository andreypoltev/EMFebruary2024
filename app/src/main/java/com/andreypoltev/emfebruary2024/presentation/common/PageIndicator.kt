package com.andreypoltev.emfebruary2024.presentation.common

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.andreypoltev.emfebruary2024.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PageIndicator(pagerState: PagerState, modifier: Modifier, size: Dp) {

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(4.dp)
    ) {


        Row(
            Modifier
                .wrapContentHeight()
                .background(Color.White)
//                                            .fillMaxWidth()
//                                            .align(Alignment.BottomCenter)
                .padding(horizontal = 2.dp, vertical = 2.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val color =
                    if (pagerState.currentPage == iteration) colorResource(id = R.color.element_pink) else colorResource(
                        id = R.color.element_light_grey
                    )
                Box(
                    modifier = Modifier
                        .size(size + 2.dp), contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(size)
                            .clip(CircleShape)
                            .background(color)
                    )
                }
            }
        }
    }

}