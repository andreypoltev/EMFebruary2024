package com.andreypoltev.emfebruary2024.presentation.screens

import android.util.Patterns
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andreypoltev.emfebruary2024.MainViewModel
import com.andreypoltev.emfebruary2024.R
import com.andreypoltev.emfebruary2024.domain.User
import com.andreypoltev.emfebruary2024.isValidPhoneNumber
import com.andreypoltev.emfebruary2024.soFarSoGood

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(viewModel: MainViewModel) {


    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = { Text(text = stringResource(id = R.string.login)) })
    }) {
        Column(
            modifier = Modifier.padding(
                start = 16.dp,
                end = 16.dp,
                top = it.calculateTopPadding(),
                bottom = it.calculateBottomPadding()
            )
        ) {

            var name by remember { mutableStateOf("") }
            var lastName by remember { mutableStateOf("") }
            var phoneNumber by remember { mutableStateOf("") }

            var phoneIsValid by remember { mutableStateOf(false) }

            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.size(8.dp))

            TextField(
                value = lastName,
                onValueChange = { lastName = it },
                label = { Text("Last Name") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.size(8.dp))

            TextField(
                value = phoneNumber,
                onValueChange = {
                    if (soFarSoGood(it) && it.length <= 16) {
                        phoneNumber = it

                    }

                    phoneIsValid = isValidPhoneNumber(phoneNumber)


                },
                label = { Text("+7 ХХХ ХХХ ХХ ХХ") },
                trailingIcon = {
                    if (phoneNumber.isNotEmpty()) {
                        IconButton(onClick = { phoneNumber = "" }) {
                            Icon(Icons.Default.Clear, "Clear phone number")
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
            )
            Spacer(modifier = Modifier.size(32.dp))

            Card(shape = RoundedCornerShape(8.dp), modifier = Modifier.fillMaxWidth(), onClick = {

                viewModel.insertUser(User(phoneNumber, name, lastName))

            }) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.log_in),
                        modifier = Modifier.padding(16.dp)
                    )

                }
            }

            Text(text = "Phone is valid: $phoneIsValid")

            Spacer(modifier = Modifier.weight(1f))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = stringResource(id = R.string.eula_1),
                    fontSize = 10.sp,
                    color = colorResource(
                        id = R.color.text_grey
                    )
                )

                Text(
                    text = stringResource(id = R.string.eula_2),
                    fontSize = 10.sp,
                    color = colorResource(
                        id = R.color.text_grey
                    )
                )

            }


        }
    }


}