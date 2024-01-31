package com.andreypoltev.emfebruary2024

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.andreypoltev.emfebruary2024.screens.CartScreen
import com.andreypoltev.emfebruary2024.screens.CatalogScreen
import com.andreypoltev.emfebruary2024.screens.FavoritesScreen
import com.andreypoltev.emfebruary2024.screens.ItemDetailsScreen
import com.andreypoltev.emfebruary2024.screens.MainScreen
import com.andreypoltev.emfebruary2024.screens.OffersScreen
import com.andreypoltev.emfebruary2024.screens.ProfileScreen
import com.andreypoltev.emfebruary2024.screens.topbars.FavoritesTopBar
import com.andreypoltev.emfebruary2024.screens.topbars.ItemDetailsTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation(viewModel: MainViewModel) {

    val navController = rememberNavController()


    val items = listOf(Screen.Main, Screen.CatalogRoute, Screen.Cart, Screen.Offers, Screen.Profile)

//    var paddingValues by remember { mutableStateOf(16.dp)}

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(topBar = {

        Log.d("CurrentDestination", currentDestination?.route.toString())

        when (currentDestination?.route) {

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
//                CenterAlignedTopAppBar(title = { Text(text = stringResource(id = Screen.ItemDetails.resourceId)) })

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


    }, bottomBar = {

        BottomNavigation {


            items.forEach { screen ->
                BottomNavigationItem(
                    selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                    onClick = {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
//                    modifier = Modifier.padding(bottom = paddingValues),
                    icon = { Icon(imageVector = Icons.Filled.Favorite, contentDescription = null) },
                    label = {
                        Text(text = stringResource(id = screen.resourceId))
                    }
                )

            }

        }

    }) {

//        paddingValues = it.calculateBottomPadding()


        NavHost(
            navController = navController,
            startDestination = Screen.Profile.route,
            modifier = Modifier.padding(
                start = 16.dp,
                end = 16.dp,
                top = it.calculateTopPadding(),
                bottom = it.calculateBottomPadding()
            )
        ) {
            composable(Screen.Main.route) {
                MainScreen(viewModel = viewModel, navController = navController)
            }

            navigation(startDestination = Screen.Catalog.route, Screen.CatalogRoute.route) {

                composable(Screen.Catalog.route) {
                    CatalogScreen(viewModel = viewModel, navController = navController)
                }

                composable(Screen.ItemDetails.route + "/{itemId}") {
                    ItemDetailsScreen(
                        viewModel = viewModel,
                        navController = navController,
                        id = it.arguments?.getString("itemId")
                    )
                }

            }







            composable(Screen.Cart.route) {
                CartScreen(viewModel, navController)
            }

            composable(Screen.Offers.route) {
                OffersScreen(viewModel, navController)
            }

            composable(Screen.Profile.route) {
                ProfileScreen(viewModel = viewModel, navController = navController)
            }

            composable(Screen.Favorites.route) {
                FavoritesScreen(viewModel, navController)
            }

        }
    }
}