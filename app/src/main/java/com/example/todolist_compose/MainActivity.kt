package com.example.todolist_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.todolist_compose.ui.screens.CreateTaskScreen
import com.example.todolist_compose.ui.screens.HomeScreen
import com.example.todolist_compose.viewModel.CreateTaskViewModel
import com.example.todolist_compose.viewModel.HomeViewModel

class MainActivity : ComponentActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            TaskDatabase::class.java,
            "task.db"
        ).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            nav(db)
        }
    }
}

val localNavController = compositionLocalOf<NavHostController> {
    error("NavController not provided") }

@Composable
fun nav(taskDatabase: TaskDatabase){
    val navController = rememberNavController()
    CompositionLocalProvider(localNavController provides navController) {
        NavHost(navController = navController, startDestination = "home"){
            composable("home"){
                val viewModel = viewModel<HomeViewModel>(
                    factory = object :ViewModelProvider.Factory{
                        override fun <T : ViewModel> create(modelClass: Class<T>): T {
                            return HomeViewModel(
                                taskDatabase.dao, navController
                            ) as T
                        }
                    }
                )
                val state by viewModel.state.collectAsState()
                HomeScreen(state, viewModel::onEvent)
            }
            composable("newTask"){
                val viewModel = viewModel<CreateTaskViewModel>(
                    factory = object :ViewModelProvider.Factory{
                        override fun <T : ViewModel> create(modelClass: Class<T>): T {
                            return CreateTaskViewModel(
                                taskDatabase.dao, navController
                            ) as T
                        }
                    }
                )
                val state by viewModel.state.collectAsState()
                CreateTaskScreen(state, viewModel::onEvent)
            }
        }
    }
}