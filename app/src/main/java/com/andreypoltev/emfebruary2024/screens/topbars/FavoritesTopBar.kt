package com.andreypoltev.emfebruary2024.screens.topbars

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.andreypoltev.emfebruary2024.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesTopBar(navController: NavHostController) {
    CenterAlignedTopAppBar(navigationIcon = {
        androidx.compose.material.IconButton(onClick = { navController.popBackStack() }) {
            Icon(
                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                contentDescription = "Back"
            )

        }
    }, title = { Text(text = stringResource(id = Screen.Favorites.resourceId)) })

}