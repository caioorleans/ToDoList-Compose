package com.example.todolist_compose.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todolist_compose.ui.components.AppBottomBar
import com.example.todolist_compose.ui.components.CustomDrawer
import com.example.todolist_compose.ui.components.CustomTextField
import com.example.todolist_compose.ui.components.DatePickerComponent
import com.example.todolist_compose.ui.components.OtherTopAppBar
import com.example.todolist_compose.ui.theme.ToDOListComposeTheme

@Preview(showBackground = true)
@Composable
fun CreateTaskScreen(){
    ToDOListComposeTheme {
        Scaffold(
            topBar = {
                OtherTopAppBar(
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
            },
            bottomBar = {
                AppBottomBar(text = "Criar Task", {})
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(12.dp)
            ) {
                CustomTextField()
                CustomDrawer(title = "Descrição"){
                    OutlinedTextField(
                        value = "",
                        onValueChange = {it},
                        colors = OutlinedTextFieldDefaults.colors(
                            disabledBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent,
                            focusedBorderColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                DatePickerComponent()
            }
        }
    }
}