package com.andreypoltev.emfebruary2024.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.andreypoltev.emfebruary2024.MainViewModel
import com.andreypoltev.emfebruary2024.R
import com.andreypoltev.emfebruary2024.Screen
import com.andreypoltev.emfebruary2024.presentation.composables.CustomProgressIndicator
import com.andreypoltev.emfebruary2024.presentation.composables.DetailsCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(viewModel: MainViewModel, navController: NavHostController) {

    val users by viewModel.flowUsers().collectAsState(initial = emptyList())

    val itemsInFavorites by viewModel.flowRowCount().collectAsState(initial = 0)

    val context = LocalContext.current


    if (users.isNotEmpty()) {

        val user = users.getOrNull(0)


        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {

            if (user != null) {
                DetailsCard(
                    icon = R.drawable.profile_icon,
                    title = "${user.name} ${user.lastName}",
                    text = user.phoneNumber
                )
            }



            Spacer(modifier = Modifier.size(24.dp))

            DetailsCard(
                icon = R.drawable.heart_outlined,
                tint = R.color.element_pink,
                title = stringResource(id = R.string.favorites),
                text = "$itemsInFavorites ${stringResource(id = R.string.item)}"
            ) {
                if (itemsInFavorites != 0) {
                    navController.navigate(Screen.Favorites.route)
                } else {
                    val message = context.getString(R.string.favorites_missing)
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                }
            }
            DetailsCard(
                icon = R.drawable.stores,
                tint = R.color.element_pink,
                title = stringResource(id = R.string.stores),
                text = null
            )
            DetailsCard(
                icon = R.drawable.feedback,
                tint = R.color.element_orange,
                title = stringResource(id = R.string.feedback),
                text = null
            )

            DetailsCard(
                icon = R.drawable.eula,
                tint = R.color.text_grey,
                title = stringResource(id = R.string.eula),
                text = null
            )

            DetailsCard(
                icon = R.drawable.returns,
                tint = R.color.text_grey,
                title = stringResource(id = R.string.returns),
                text = null
            )

            Spacer(modifier = Modifier.weight(1f))
            Card(modifier = Modifier.fillMaxWidth(), onClick = {
                viewModel.clearDb()

            }) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.log_out),
                        modifier = Modifier.padding(16.dp)
                    )
                }

            }


            Spacer(modifier = Modifier.size(32.dp))

        }
    } else {
        CustomProgressIndicator()
    }
}