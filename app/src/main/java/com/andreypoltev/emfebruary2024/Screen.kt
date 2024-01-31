package com.andreypoltev.emfebruary2024

import androidx.annotation.StringRes

sealed class Screen(val route: String, @StringRes val resourceId: Int) {

    object Login : Screen("login_screen", R.string.login)
    object Favorites : Screen("favorites_screen", R.string.favorites)
    object Main : Screen("main_screen", R.string.main)

    object CatalogRoute : Screen("catalog_screen_route", R.string.catalog)
    object Catalog : Screen("catalog_screen", R.string.catalog)
    object ItemDetails : Screen("item_details_screen", R.string.details)
    object Cart : Screen("cart_screen", R.string.cart)
    object Offers : Screen("offers_screen", R.string.offers)
    object Profile : Screen("profile_screen", R.string.profile)

}