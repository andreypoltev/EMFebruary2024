package com.andreypoltev.emfebruary2024

import android.util.Log
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.andreypoltev.emfebruary2024.presentation.composables.topbars.CustomTopBar
import com.andreypoltev.emfebruary2024.presentation.screens.CartScreen
import com.andreypoltev.emfebruary2024.presentation.screens.CatalogScreen
import com.andreypoltev.emfebruary2024.presentation.screens.FavoritesScreen
import com.andreypoltev.emfebruary2024.presentation.screens.ItemDetailsScreen
import com.andreypoltev.emfebruary2024.presentation.screens.LoginScreen
import com.andreypoltev.emfebruary2024.presentation.screens.MainScreen
import com.andreypoltev.emfebruary2024.presentation.screens.OffersScreen
import com.andreypoltev.emfebruary2024.presentation.screens.ProfileScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation(viewModel: MainViewModel) {

    val usersCount by viewModel.flowUserCount().collectAsState(initial = null)

    if (usersCount != null) {


        val navController = rememberNavController()
        val startDestination =
            if (usersCount == 0) Screen.Login.route else Screen.CatalogRoute.route

        val items =
            listOf(Screen.Main, Screen.CatalogRoute, Screen.Cart, Screen.Offers, Screen.Profile)


        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination



        Scaffold(topBar = {

            Log.d("CurrentDestination", currentDestination?.route.toString())

            CustomTopBar(currentDestination?.route, navController)


        }, bottomBar = {

            if (currentDestination?.route != Screen.Login.route) {


                BottomNavigation(backgroundColor = MaterialTheme.colorScheme.background) {


                    items.forEach { screen ->
                        BottomNavigationItem(
                            modifier = Modifier
                                .padding(top = 8.dp)
                                .navigationBarsPadding(),
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
                            icon = {

                                val selected =
                                    currentDestination?.hierarchy?.any { it.route == screen.route }

                                if (screen.icon != null)
                                    Icon(
                                        modifier = Modifier.padding(top = 4.dp, bottom = 12.dp),
                                        painter = painterResource(id = screen.icon),
                                        contentDescription = "",
                                        tint = if (selected == true) colorResource(id = R.color.element_pink) else colorResource(
                                            id = R.color.element_dark_grey
                                        )
                                    )
                            },
                            label = {
                                val selected =
                                    currentDestination?.hierarchy?.any { it.route == screen.route }

                                Text(
                                    text = stringResource(id = screen.resourceId),
                                    color = if (selected == true)
                                        colorResource(id = R.color.text_pink) else colorResource(id = R.color.text_dark_grey)
                                )
                            }
                        )

                    }


                }

            }
        }) {


            NavHost(
                navController = navController,
                startDestination = startDestination,
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


                composable(Screen.Login.route) {
                    LoginScreen(viewModel, navController)
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
}