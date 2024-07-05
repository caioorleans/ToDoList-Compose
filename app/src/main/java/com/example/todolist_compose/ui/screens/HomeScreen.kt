package com.example.todolist_compose.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.res.ResourcesCompat.ThemeCompat
import com.example.todolist_compose.ui.components.BottomNavBar
import com.example.todolist_compose.ui.components.HomeTopBar
import com.example.todolist_compose.ui.components.TripleSwitch
import com.example.todolist_compose.ui.theme.ToDOListComposeTheme

@Preview(showBackground = true)
@Composable
fun HomeScreen(modifier: Modifier = Modifier){
    ToDOListComposeTheme{
        Scaffold(
            topBar = {
                HomeTopBar(modifier.padding(horizontal = 12.dp))
            },
            bottomBar = {
                BottomNavBar()
            }
        ) { paddingValues ->
            Column(
                modifier
                    .padding(paddingValues)
                    .padding(horizontal = 12.dp)
            ) {
                TripleSwitch()
            }
        }
    }
}