package com.andreypoltev.emfebruary2024.presentation.favorites_screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.andreypoltev.emfebruary2024.MainViewModel
import com.andreypoltev.emfebruary2024.R
import com.andreypoltev.emfebruary2024.presentation.common.ItemsGrid

@Composable
fun FavoritesScreen(viewModel: MainViewModel, navController: NavHostController) {

    val favorites by viewModel.flowFavorites().collectAsState(initial = emptyList())

    if (favorites.isNotEmpty()) {

        ItemsGrid(viewModel = viewModel, items = favorites, navController = navController)

    } else {
        Text(text = stringResource(id = R.string.favorites_missing))
    }

}