package com.andreypoltev.emfebruary2024.presentation.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.andreypoltev.emfebruary2024.MainViewModel
import com.andreypoltev.emfebruary2024.data.model.Item

@Composable
fun FavoritesButton(viewModel: MainViewModel, item: Item, modifier: Modifier) {

    val favorites by viewModel.flowFavorites().collectAsState(initial = emptyList())

    IconButton(
        onClick = { viewModel.addOrRemoveItem(item) },
        modifier = modifier
    ) {
        Icon(
            imageVector = if (item in favorites) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
            contentDescription = null
        )
    }

}