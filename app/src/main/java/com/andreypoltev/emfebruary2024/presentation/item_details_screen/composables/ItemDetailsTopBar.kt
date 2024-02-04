package com.andreypoltev.emfebruary2024.presentation.item_details_screen.composables

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.andreypoltev.emfebruary2024.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemDetailsTopBar(navController: NavHostController) {
    CenterAlignedTopAppBar(colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White),
        navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon(
                    modifier = Modifier.size(28.dp),
                    painter = painterResource(id = R.drawable.back_arrow_icon),
                    contentDescription = null
                )

            }
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    modifier = Modifier.size(28.dp),
                    painter = painterResource(id = R.drawable.share_icon),
                    contentDescription = null
                )

            }
        },
        title = {})

}