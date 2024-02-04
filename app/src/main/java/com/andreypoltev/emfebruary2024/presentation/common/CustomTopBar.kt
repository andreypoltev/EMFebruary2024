package com.andreypoltev.emfebruary2024.presentation.common

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.andreypoltev.emfebruary2024.util.Screen
import com.andreypoltev.emfebruary2024.presentation.favorites_screen.FavoritesTopBar
import com.andreypoltev.emfebruary2024.presentation.item_details_screen.composables.ItemDetailsTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(route: String?, navController: NavHostController) {

    when (route) {

        Screen.Main.route -> {
            CustomCenterAlignedTopAppBar(Screen.Main.resourceId)
        }

        Screen.Cart.route -> {
            CustomCenterAlignedTopAppBar(Screen.Cart.resourceId)

        }

        Screen.Login.route -> {
            CustomCenterAlignedTopAppBar(Screen.Login.resourceId)

        }

        Screen.Catalog.route -> {
            CustomCenterAlignedTopAppBar(Screen.Catalog.resourceId)

        }

        Screen.ItemDetails.route + "/{itemId}" -> {
            ItemDetailsTopBar(navController)

        }

        Screen.Offers.route -> {
            CustomCenterAlignedTopAppBar(Screen.Offers.resourceId)

        }

        Screen.Profile.route -> {
            CustomCenterAlignedTopAppBar(Screen.Profile.resourceId)

        }

        Screen.Favorites.route -> {
            FavoritesTopBar(navController = navController)


        }


    }

}