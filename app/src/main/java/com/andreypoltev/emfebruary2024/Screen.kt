package com.andreypoltev.emfebruary2024

import androidx.annotation.StringRes

sealed class Screen(val route: String, @StringRes val resourceId: Int, val icon: Int?) {

    object Login : Screen("login_screen", R.string.login, null)
    object Favorites : Screen("favorites_screen", R.string.favorites, null)
    object Main : Screen("main_screen", R.string.main, R.drawable.home_icon)
    object CatalogRoute : Screen("catalog_screen_route", R.string.catalog, R.drawable.catalog_icon)
    object Catalog : Screen("catalog_screen", R.string.catalog, null)
    object ItemDetails : Screen("item_details_screen", R.string.details, null)
    object Cart : Screen("cart_screen", R.string.cart, R.drawable.cart_icon)
    object Offers : Screen("offers_screen", R.string.offers, R.drawable.offers_icon)
    object Profile : Screen("profile_screen", R.string.profile, R.drawable.profile_icon)

}