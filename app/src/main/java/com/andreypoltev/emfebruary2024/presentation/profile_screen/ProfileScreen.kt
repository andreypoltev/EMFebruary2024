package com.andreypoltev.emfebruary2024.presentation.profile_screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.andreypoltev.emfebruary2024.MainViewModel
import com.andreypoltev.emfebruary2024.R
import com.andreypoltev.emfebruary2024.util.Screen
import com.andreypoltev.emfebruary2024.presentation.common.CustomProgressIndicator

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
                    leadingIcon = R.drawable.profile_icon,
                    tint = R.color.element_dark_grey,
                    tintTrailing = R.color.element_dark_grey,
                    title = "${user.name} ${user.lastName}",
                    text = user.phoneNumber,
                    trailingIcon = R.drawable.logout_button
                )
            }



            Spacer(modifier = Modifier.size(24.dp))

            DetailsCard(
                leadingIcon = R.drawable.heart_outlined,
                tint = R.color.element_pink,
                tintTrailing = R.color.element_black,
                title = stringResource(id = R.string.favorites),
                text = "$itemsInFavorites ${stringResource(id = R.string.item)}",
                trailingIcon = R.drawable.forward_button
            ) {
                if (itemsInFavorites != 0) {
                    navController.navigate(Screen.Favorites.route)
                } else {
                    val message = context.getString(R.string.favorites_missing)
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                }
            }
            DetailsCard(
                leadingIcon = R.drawable.stores,
                tint = R.color.element_pink,
                tintTrailing = R.color.element_black,
                title = stringResource(id = R.string.stores),
                text = null,
                trailingIcon = R.drawable.forward_button
            )
            DetailsCard(
                leadingIcon = R.drawable.feedback,
                tintTrailing = R.color.element_black,
                tint = R.color.element_orange,
                title = stringResource(id = R.string.feedback),
                text = null,
                trailingIcon = R.drawable.forward_button
            )

            DetailsCard(
                leadingIcon = R.drawable.eula,
                tintTrailing = R.color.element_black,
                tint = R.color.text_grey,
                title = stringResource(id = R.string.eula),
                text = null,
                trailingIcon = R.drawable.forward_button
            )

            DetailsCard(
                leadingIcon = R.drawable.returns,
                tintTrailing = R.color.element_black,
                tint = R.color.text_grey,
                title = stringResource(id = R.string.returns),
                text = null,
                trailingIcon = R.drawable.forward_button
            )

            Spacer(modifier = Modifier.weight(1f))

            Card(
                colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.background_light_grey)),
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    viewModel.clearDb()

                }) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.log_out),
                        color = colorResource(id = R.color.text_black),

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