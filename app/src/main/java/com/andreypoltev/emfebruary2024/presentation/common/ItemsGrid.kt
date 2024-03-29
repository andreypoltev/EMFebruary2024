package com.andreypoltev.emfebruary2024.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.andreypoltev.emfebruary2024.MainViewModel
import com.andreypoltev.emfebruary2024.data.model.Item
import com.andreypoltev.emfebruary2024.util.Screen

@Composable
fun ItemsGrid(viewModel: MainViewModel, items: List<Item>, navController: NavHostController) {

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalItemSpacing = 8.dp
//        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items) { item ->
            ItemCard(
                viewModel,
                item
            ) { navController.navigate(Screen.ItemDetails.route + "/${item.id}") }
        }


    }

}