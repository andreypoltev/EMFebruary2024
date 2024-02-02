package com.andreypoltev.emfebruary2024.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import com.andreypoltev.emfebruary2024.MainViewModel
import com.andreypoltev.emfebruary2024.presentation.composables.CustomProgressIndicator
import com.andreypoltev.emfebruary2024.presentation.composables.ItemsGrid

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogScreen(viewModel: MainViewModel, navController: NavHostController) {

    val items by viewModel.items.collectAsState()

//    Text(text = "CatalogScreen")

//    if (false) {
    if (items.isNotEmpty()) {
        Column {
            Row {
                Text(text = "По популярности")
            }

        ItemsGrid(viewModel, items, navController)
        }



    } else {
        CustomProgressIndicator()
    }


}