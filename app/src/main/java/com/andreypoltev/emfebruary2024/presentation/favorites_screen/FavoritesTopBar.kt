package com.andreypoltev.emfebruary2024.presentation.favorites_screen

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.andreypoltev.emfebruary2024.R
import com.andreypoltev.emfebruary2024.util.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesTopBar(navController: NavHostController) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White),
        navigationIcon = {
            androidx.compose.material.IconButton(onClick = { navController.popBackStack() }) {

                Icon(
                    painter = painterResource(id = R.drawable.back_arrow_icon),
                    contentDescription = null
                )

            }
        },
        title = { Text(text = stringResource(id = Screen.Favorites.resourceId)) })

}