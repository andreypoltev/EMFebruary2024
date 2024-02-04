package com.andreypoltev.emfebruary2024.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.andreypoltev.emfebruary2024.MainViewModel
import com.andreypoltev.emfebruary2024.R
import com.andreypoltev.emfebruary2024.SortType

@Composable
fun SortDropdownMenu(
    viewModel: MainViewModel,
    onClick: () -> Unit,
    isExpanded: MutableState<Boolean>,
    onDismissRequest: () -> Unit
) {

    var sortType by remember { mutableStateOf(R.string.sort_by_default)}


    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.background_white))
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable { onClick() }) {

            Icon(painter = painterResource(id = R.drawable.sort_icon), contentDescription = null)


            Text(text = stringResource(id = sortType), modifier = Modifier.padding(8.dp))
            DropdownMenu(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(colorResource(id = R.color.background_light_grey)),
                expanded = isExpanded.value,
                onDismissRequest = onDismissRequest
            ) {

                DropdownMenuItem(
                    text = { Text(text = stringResource(id = R.string.sort_by_default)) },
                    onClick = {
                        viewModel.setSortType(SortType.byDefault)
                        sortType = R.string.sort_by_default
                        isExpanded.value = false
                    })

                DropdownMenuItem(
                    text = { Text(text = stringResource(id = R.string.sort_rating)) },
                    onClick = {
                        viewModel.setSortType(SortType.byRating)
                        sortType = R.string.sort_rating
                        isExpanded.value = false
                    })

                DropdownMenuItem(
                    text = { Text(text = stringResource(id = R.string.sort_by_price_desc)) },
                    onClick = {
                        viewModel.setSortType(SortType.byPriceDesc)
                        sortType = R.string.sort_by_price_desc
                        isExpanded.value = false
                    })

                DropdownMenuItem(
                    text = { Text(text = stringResource(id = R.string.sort_by_price_asc)) },
                    onClick = {
                        viewModel.setSortType(SortType.byPriceAsc)
                        sortType = R.string.sort_by_price_asc
                        isExpanded.value = false
                    })


            }
//            Spacer(modifier = Modifier.size(8.dp))


            Icon(
                painter = painterResource(id = R.drawable.sort_dropdown_icon),
                contentDescription = null
            )


        }
    }
}