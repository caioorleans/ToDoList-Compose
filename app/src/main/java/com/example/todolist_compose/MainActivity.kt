package com.example.todolist_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todolist_compose.ui.screens.CreateTaskScreen
import com.example.todolist_compose.ui.screens.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            nav()
        }
    }
}

val localNavController = compositionLocalOf<NavHostController> {
    error("NavController not provided") }

@Composable
fun nav(){
    val navController = rememberNavController()
    CompositionLocalProvider(localNavController provides navController) {
        NavHost(navController = navController, startDestination = "home"){
            composable("home"){
                HomeScreen()
            }
            composable("newTask"){
                CreateTaskScreen()
            }
        }
    }
}