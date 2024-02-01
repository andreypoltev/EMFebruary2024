package com.andreypoltev.emfebruary2024.presentation.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.andreypoltev.emfebruary2024.R
import com.andreypoltev.emfebruary2024.data.model.Info

@Composable
fun Details(info: List<Info>) {
    Text(text = stringResource(id = R.string.details), style = MaterialTheme.typography.titleLarge)
    Spacer(modifier = Modifier.size(16.dp))
    Column(modifier = Modifier.fillMaxWidth()) {
        info.forEach {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, bottom = 4.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = it.title)
                Spacer(modifier = Modifier.weight(1f))
                Text(text = it.value, textAlign = TextAlign.End)
            }
            HorizontalDivider()
        }

    }


}