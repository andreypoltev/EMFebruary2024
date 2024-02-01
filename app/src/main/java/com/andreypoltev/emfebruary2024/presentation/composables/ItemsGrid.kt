package com.andreypoltev.emfebruary2024.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.andreypoltev.emfebruary2024.MainViewModel
import com.andreypoltev.emfebruary2024.Screen
import com.andreypoltev.emfebruary2024.data.model.Item

@Composable
fun ItemsGrid(viewModel: MainViewModel, items: List<Item>, navController: NavHostController) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items) { item ->
            ItemCard(viewModel, item) { navController.navigate(Screen.ItemDetails.route + "/${item.id}") }
        }


    }

}