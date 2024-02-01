package com.andreypoltev.emfebruary2024.presentation.composables.topbars

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.andreypoltev.emfebruary2024.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(route: String?, navController: NavHostController) {

    when (route) {

        Screen.Main.route -> {
            CenterAlignedTopAppBar(title = { Text(text = stringResource(id = Screen.Main.resourceId)) })

        }

        Screen.Cart.route -> {
            CenterAlignedTopAppBar(title = { Text(text = stringResource(id = Screen.Cart.resourceId)) })

        }

        Screen.Catalog.route -> {
            CenterAlignedTopAppBar(title = { Text(text = stringResource(id = Screen.Catalog.resourceId)) })

        }

        Screen.ItemDetails.route + "/{itemId}" -> {
            ItemDetailsTopBar(navController)

        }

        Screen.Offers.route -> {
            CenterAlignedTopAppBar(title = { Text(text = stringResource(id = Screen.Offers.resourceId)) })

        }

        Screen.Profile.route -> {
            CenterAlignedTopAppBar(title = { Text(text = stringResource(id = Screen.Profile.resourceId)) })

        }

        Screen.Favorites.route -> {
            FavoritesTopBar(navController = navController)


        }


    }

}