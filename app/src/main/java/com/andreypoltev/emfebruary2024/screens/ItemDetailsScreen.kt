package com.andreypoltev.emfebruary2024.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.andreypoltev.emfebruary2024.MainViewModel
import com.andreypoltev.emfebruary2024.screens.composables.FeedbackRow
import com.andreypoltev.emfebruary2024.screens.composables.PriceRow
import com.andreypoltev.emfebruary2024.R
import com.andreypoltev.emfebruary2024.screens.composables.BrandCard
import com.andreypoltev.emfebruary2024.screens.composables.CustomBottomBar
import com.andreypoltev.emfebruary2024.screens.composables.CustomProgressIndicator
import com.andreypoltev.emfebruary2024.screens.composables.Details

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemDetailsScreen(viewModel: MainViewModel, navController: NavHostController, id: String?) {

    val items by viewModel.items.collectAsState()

    val item = items.find { it.id == id.toString() }

    if (item != null) {

        Column(
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                )
                .verticalScroll(rememberScrollState())
        ) {

            Text(text = item.id)
            Text(text = item.title)
            Text(text = item.subtitle)


            // TODO "Отображаемое значение формируется так: “Доступно для заказа {available} штук”. В зависимости от значения available, последнее слово склоняется: “штука”, “штуки”, “штуки”."
            Text(text = "Доступно для заказа ${item.available} штук")

            Spacer(modifier = Modifier.size(10.dp))

            Divider()

            Spacer(modifier = Modifier.size(10.dp))


            if (item.feedback != null) {
                FeedbackRow(item.feedback)
            }

            PriceRow(item.price)

            Spacer(modifier = Modifier.size(24.dp))

            var visible by remember { mutableStateOf(true) }

            Text(
                text = stringResource(id = R.string.description),
                style = MaterialTheme.typography.titleLarge
            )

            AnimatedVisibility(visible = visible) {
                Column {

                    Spacer(modifier = Modifier.size(16.dp))
                    BrandCard(item.title)
                    Spacer(modifier = Modifier.size(8.dp))

                    Text(text = item.description)

                    Spacer(modifier = Modifier.size(10.dp))
                }
            }

            Text(
                text = if (!visible) stringResource(id = R.string.show_description) else stringResource(
                    id = R.string.hide_description
                ), modifier = Modifier.clickable {
                    visible = !visible
                }
            )
            Spacer(modifier = Modifier.size(34.dp))

            Details(item.info)

            Spacer(modifier = Modifier.size(34.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {

                Text(
                    text = stringResource(id = R.string.ingridients),
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.weight(1f))

                // TODO needs copy icon
                IconButton(onClick = { }) {
                    Icon(imageVector = Icons.Default.Send, contentDescription = "Copy")

                }

            }



            Spacer(modifier = Modifier.size(16.dp))

//                Text(text = item.ingredients, maxLines = 2)

            var ingridientsAreVisible by remember { mutableStateOf(false) }

            var hasVisualOverflow by remember { mutableStateOf(false) }

            Text(
                text = "${item.ingredients}${item.ingredients}",
                maxLines = if (ingridientsAreVisible) Int.MAX_VALUE else 2,
                overflow = TextOverflow.Ellipsis, onTextLayout = { textLayoutResult ->
                    hasVisualOverflow = textLayoutResult.hasVisualOverflow

                }
            )

            // TODO условия туплю
            Text(
                text = if (!ingridientsAreVisible) stringResource(id = R.string.show_description) else stringResource(
                    id = R.string.hide_description
                ), modifier = Modifier.clickable {
                    ingridientsAreVisible = !ingridientsAreVisible
                }
            )


        }


    } else {
        CustomProgressIndicator()
    }
}