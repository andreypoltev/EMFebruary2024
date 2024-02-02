package com.andreypoltev.emfebruary2024.presentation.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CustomHorizontalPager(images: List<Int>, pagerState: PagerState) {
    HorizontalPager(state = pagerState, modifier = Modifier.fillMaxWidth()) {
        Image(
            contentScale = ContentScale.FillHeight,
            painter = painterResource(id = images[it]),
            contentDescription = null,
            modifier = Modifier.height(368.dp)
        )

    }
}