package com.andreypoltev.emfebruary2024.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.andreypoltev.emfebruary2024.MainViewModel

@Composable
fun CartScreen(viewModel: MainViewModel, navController: NavHostController) {
    Text(text = "CartScreen")
}