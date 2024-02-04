package com.andreypoltev.emfebruary2024.presentation.catalog_screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.andreypoltev.emfebruary2024.MainViewModel
import com.andreypoltev.emfebruary2024.R
import com.andreypoltev.emfebruary2024.presentation.common.CustomProgressIndicator
import com.andreypoltev.emfebruary2024.presentation.common.ItemsGrid
import com.andreypoltev.emfebruary2024.util.Tag


@Composable
fun CatalogScreen(viewModel: MainViewModel, navController: NavHostController) {

    val itemsFilteredAndSorted by viewModel.items().collectAsState(initial = emptyList())

    val tags = listOf(
        Tag("show_all", R.string.show_all_tags),
        Tag("face", R.string.face),
        Tag("body", R.string.body),
        Tag("suntan", R.string.suntan),
        Tag("mask", R.string.mask)
    )

    if (itemsFilteredAndSorted.isNotEmpty()) {
        Column {

            Row {


                val isExpanded = remember { mutableStateOf(false) }

                SortDropdownMenu(
                    viewModel = viewModel,
                    onClick = { isExpanded.value = !isExpanded.value },
                    isExpanded = isExpanded
                ) { isExpanded.value = false }


                Spacer(modifier = Modifier.weight(1f))

                Card(
                    onClick = {},
                    colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.background_white))
                ) {

                    Row(verticalAlignment = Alignment.CenterVertically) {

                        Icon(
                            painter = painterResource(id = R.drawable.filter_icon),
                            contentDescription = null
                        )

                        Text(
                            modifier = Modifier.padding(8.dp),
                            text = stringResource(id = R.string.filters)
                        )
                    }
                }
            }

            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {


                items(tags) { tag ->
                    TagFilterCard(viewModel, tag.stringResource, tag.tag) {
                        Log.d("", "Tag is: ${tag.tag}")
                        viewModel.setTag(tag.tag)
                    }

                }

            }



            Spacer(modifier = Modifier.size(24.dp))

            ItemsGrid(viewModel, itemsFilteredAndSorted, navController)
        }


    } else {
        CustomProgressIndicator()
    }


}