package com.andreypoltev.emfebruary2024.presentation.catalog_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.andreypoltev.emfebruary2024.MainViewModel
import com.andreypoltev.emfebruary2024.R
import com.andreypoltev.emfebruary2024.util.Tags

@Composable
fun TagFilterCard(viewModel: MainViewModel, resourceId: Int, tag: String, onClick: () -> Unit) {

    val currentTag by viewModel.filterTag.collectAsState()

    Card(
        onClick = onClick,
        shape = RoundedCornerShape(100.dp), colors = CardDefaults.cardColors(
            containerColor = colorResource(
                id = if (currentTag == tag) R.color.element_dark_blue else R.color.background_light_grey
            )
        )
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                color = colorResource(id = if (currentTag == tag) R.color.text_white else R.color.text_grey),
                text = stringResource(id = resourceId),
                modifier = if (currentTag == tag) Modifier.padding(
                    start = 12.dp,
                    end = 4.dp,
                    top = 4.dp,
                    bottom = 4.dp
                ) else Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
            )

            if (currentTag == tag) {
                Icon(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .clickable { viewModel.setTag(Tags.showAll.tag) },
                    painter = painterResource(id = R.drawable.remove_tag_icon),
                    tint = colorResource(id = R.color.element_white),
                    contentDescription = null
                )

            }

        }


    }

}