package com.andreypoltev.emfebruary2024.presentation.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.andreypoltev.emfebruary2024.MainViewModel
import com.andreypoltev.emfebruary2024.presentation.composables.CustomProgressIndicator
import com.andreypoltev.emfebruary2024.presentation.composables.ItemsGrid
import com.andreypoltev.emfebruary2024.presentation.composables.TagFilterCard
import com.andreypoltev.emfebruary2024.R

data class Tag(
    val tag: String,
    val stringResource: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogScreen(viewModel: MainViewModel, navController: NavHostController) {

    val items by viewModel.items.collectAsState()

    val filteredItems by viewModel.itemsFilteredByTag.collectAsState()

    val tags = listOf(
        Tag("show_all", R.string.show_all_tags),
        Tag("face", R.string.face),
        Tag("body", R.string.body),
        Tag("suntan", R.string.suntan),
        Tag("mask", R.string.mask)
    )

    if (items.isNotEmpty()) {
        Column {

            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {


                items(tags) { tag ->
                    TagFilterCard(tag.stringResource, onClick = {
                        Log.d("", "Tag is: ${tag.tag}")
                        viewModel.setTag(tag.tag)
                    })

                }

            }



            Spacer(modifier = Modifier.size(24.dp))

            ItemsGrid(viewModel, filteredItems, navController)
        }


    } else {
        CustomProgressIndicator()
    }


}