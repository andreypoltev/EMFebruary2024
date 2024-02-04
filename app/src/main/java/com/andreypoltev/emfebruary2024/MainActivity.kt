package com.andreypoltev.emfebruary2024

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.andreypoltev.emfebruary2024.domain.LocalDatabase
import com.andreypoltev.emfebruary2024.presentation.login_screen.LoginScreen
import com.andreypoltev.emfebruary2024.ui.theme.EMFebruary2024Theme

class MainActivity : ComponentActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            LocalDatabase::class.java,
            LocalDatabase.DATABASE_NAME


        ).build()
    }


    private val viewModel by viewModels<MainViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return MainViewModel(db.itemDao, db.userDao) as T
                }
            }
        }
    )


//    private val db by lazy {
//        Room.databaseBuilder(
//            applicationContext,
//            UserDataBase::class.java,
//            UserDataBase.DATABASE_NAME
//        ).build()
//    }


    //    private val viewModel by viewModels<MainViewModel>(
//        factoryProducer = {
//            object : ViewModelProvider.Factory {
//                override fun <T : ViewModel> create(modelClass: Class<T>): T {
//                    return MainViewModel(db.dao) as T
//                }
//            }
//        }
//    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EMFebruary2024Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val usersCount by viewModel.flowUserCount().collectAsState(null)

                    if (usersCount != null) {

                        if (usersCount == 0) {
                            LoginScreen(viewModel = viewModel)
                        } else {
                            Navigation(viewModel = viewModel)
                        }

                    }

                }
            }
        }
    }
}