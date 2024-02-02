package com.andreypoltev.emfebruary2024.presentation.composables

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import com.andreypoltev.emfebruary2024.MainViewModel
import com.andreypoltev.emfebruary2024.R
import com.andreypoltev.emfebruary2024.data.model.Item

@Composable
fun FavoritesButton(viewModel: MainViewModel, item: Item, modifier: Modifier) {

    val favorites by viewModel.flowFavorites().collectAsState(initial = emptyList())

    IconButton(
        onClick = { viewModel.addOrRemoveItem(item) },
        modifier = modifier
    ) {

        Icon(
            painter = if (item in favorites) painterResource(id = R.drawable.heart_filled) else painterResource(
                id = R.drawable.heart_outlined
            ),
            tint = if (item in favorites) colorResource(id = R.color.element_pink) else colorResource(
                id = R.color.element_dark_grey
            ),
            contentDescription = null
        )

    }

}