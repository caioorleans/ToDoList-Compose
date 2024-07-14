package com.example.todolist_compose.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(navController: NavHostController,modifier: Modifier = Modifier){
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Projeto",
                fontWeight = FontWeight.Bold
            )
        },
        actions = {
            IconButton(onClick = {
                navController.navigate("searchTask")
            }) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
            }
        },
        modifier = modifier
            .padding(bottom = 12.dp)
    )
}