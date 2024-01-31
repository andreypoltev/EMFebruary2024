package com.andreypoltev.emfebruary2024.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.andreypoltev.emfebruary2024.MainViewModel
import com.andreypoltev.emfebruary2024.R
import com.andreypoltev.emfebruary2024.Screen
import com.andreypoltev.emfebruary2024.screens.composables.DetailsCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(viewModel: MainViewModel, navController: NavHostController) {

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {

        DetailsCard(icon = Icons.Default.AccountCircle, title = "Marina Ivanovna", text = "+7 993 877 44 02")

        Spacer(modifier = Modifier.size(24.dp))
        
        DetailsCard(
            Icons.Outlined.FavoriteBorder,
            stringResource(id = R.string.favorites),
            "1 товар",
            onClick = {
                navController.navigate(Screen.Favorites.route)
            }
        )
        DetailsCard(
            icon = Icons.Outlined.FavoriteBorder,
            title = stringResource(id = R.string.stores),
            null
        )
        DetailsCard(
            icon = Icons.Outlined.Email,
            title = stringResource(id = R.string.feedback),
            text = null
        )

        DetailsCard(
            icon = Icons.Outlined.Email,
            title = stringResource(id = R.string.eula),
            text = null
        )

        DetailsCard(
            icon = Icons.Outlined.Email,
            title = stringResource(id = R.string.returns),
            text = null
        )

    }
}