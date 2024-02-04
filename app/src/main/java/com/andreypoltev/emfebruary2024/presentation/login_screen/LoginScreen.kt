package com.andreypoltev.emfebruary2024.presentation.login_screen

import android.util.Log
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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andreypoltev.emfebruary2024.MainViewModel
import com.andreypoltev.emfebruary2024.R
import com.andreypoltev.emfebruary2024.domain.User
import com.andreypoltev.emfebruary2024.presentation.common.CustomCenterAlignedTopAppBar
import com.andreypoltev.emfebruary2024.util.Screen
import com.andreypoltev.emfebruary2024.util.isStringValid
import com.andreypoltev.emfebruary2024.util.isValidPhoneNumber
import com.andreypoltev.emfebruary2024.util.soFarSoGood

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(viewModel: MainViewModel) {

    Scaffold(topBar = { CustomCenterAlignedTopAppBar(Screen.Login.resourceId) }) {


        Column(
            modifier = Modifier.padding(
                top = it.calculateTopPadding(),
                bottom = it.calculateBottomPadding(),
                start = 16.dp,
                end = 16.dp
            )
        ) {

//            TextField(
//                modifier = Modifier.fillMaxWidth(),
//                value = test.value,
//                shape = RoundedCornerShape(12.dp),
//                onValueChange = { test.value = it },
//                label = { Text(text = title) },
//                singleLine = true,
//                colors = TextFieldDefaults.textFieldColors(
//                    containerColor = Color("#F6F6F9".toColorInt()),
//                    focusedIndicatorColor = Color.Transparent,
//                    unfocusedIndicatorColor = Color.Transparent,
//                    focusedLabelColor = Color("#A9ABB7".toColorInt()),
//                    unfocusedLabelColor = Color("#A9ABB7".toColorInt())
//                )
//            )

            val colors = TextFieldDefaults.colors(
                focusedContainerColor = colorResource(id = R.color.background_light_grey),
                unfocusedContainerColor = colorResource(id = R.color.background_light_grey),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
//                errorTextColor = Color.Red,
                focusedLabelColor = colorResource(id = R.color.text_grey),
                unfocusedLabelColor = colorResource(id = R.color.text_grey),
            )


            var name by remember { mutableStateOf("") }
            var lastName by remember { mutableStateOf("") }
            var phoneNumber by remember { mutableStateOf("") }

            var phoneIsValid by remember { mutableStateOf(false) }
            var nameIsValid by remember { mutableStateOf(false) }
            var lastNameIsValid by remember { mutableStateOf(false) }

            var validData = phoneIsValid && nameIsValid && lastNameIsValid


            TextField(
                isError = name.isNotEmpty() && !nameIsValid,
                value = name,
                onValueChange = {
                    name = it
                    nameIsValid = isStringValid(name)
                },
                label = { Text(stringResource(id = R.string.name)) },
                trailingIcon = {
                    if (name.isNotEmpty()) {
                        IconButton(onClick = { name = "" }) {
                            Icon(Icons.Default.Clear, "Clear name")
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = colors
            )

            Spacer(modifier = Modifier.size(8.dp))

            TextField(
                isError = lastName.isNotEmpty() && !lastNameIsValid,
                value = lastName,
                onValueChange = {
                    lastName = it
                    lastNameIsValid = isStringValid(lastName)
                },
                label = { Text(stringResource(id = R.string.last_name)) },
                trailingIcon = {
                    if (lastName.isNotEmpty()) {
                        IconButton(onClick = { lastName = "" }) {
                            Icon(Icons.Default.Clear, "Clear last name")
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = colors
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
                shape = RoundedCornerShape(8.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                colors = colors
            )
            Spacer(modifier = Modifier.size(32.dp))


            if (validData) {

                Card(
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {


                        viewModel.insertUser(User(phoneNumber, name, lastName))
//                    navController.navigate(Screen.Main.route)
                        Log.d("LoginScreen", Screen.Main.route)


                    },
                    colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.element_pink))
                ) {
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

            } else {
                Card(

                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.element_light_pink))
                ) {
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
            }



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