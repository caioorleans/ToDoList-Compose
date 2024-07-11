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
import com.example.todolist_compose.database.TaskDatabase
import com.example.todolist_compose.ui.screens.CreateTaskScreen
import com.example.todolist_compose.ui.screens.HomeScreen
import com.example.todolist_compose.ui.viewModel.CreateTaskViewModel
import com.example.todolist_compose.ui.viewModel.HomeViewModel
import kotlinx.coroutines.coroutineScope
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Nav()
        }
    }
}

val localNavController = compositionLocalOf<NavHostController> {
    error("NavController not provided") }

@Composable
fun Nav(){
    val navController = rememberNavController()
    CompositionLocalProvider(localNavController provides navController) {
        NavHost(navController = navController, startDestination = "home"){
            composable("home"){
                val viewModel: HomeViewModel = koinViewModel()
                val state by viewModel.state.collectAsState()
                HomeScreen(state, navController, viewModel::onEvent)
            }
            composable("newTask"){
                val viewModel:CreateTaskViewModel = koinViewModel()
                val state by viewModel.state.collectAsState()
                CreateTaskScreen(state, navController, viewModel::onEvent)
            }
            composable("editTask/{id}"){ navBackStackEntry ->
                navBackStackEntry.arguments?.getInt("id").let {
                    val viewModel:CreateTaskViewModel = koinViewModel()
                    viewModel.setTask(id)
                }
            }
        }
    }
}