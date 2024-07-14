package com.example.todolist_compose.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.todolist_compose.model.Task
import com.example.todolist_compose.ui.components.toBrazilianDateFormat
import com.example.todolist_compose.ui.theme.AppTheme
import com.example.todolist_compose.ui.viewModel.SearchTaskViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTask(navController: NavHostController, modifier: Modifier = Modifier, viewModel:SearchTaskViewModel = koinViewModel()){
    val state = viewModel.state.collectAsState()
    AppTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        OutlinedTextField(
                            value = state.value.text,
                            onValueChange = {viewModel.updateText(it)},
                            singleLine = true,
                            shape = RoundedCornerShape(12.dp),
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = "Search",
                                )
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 6.dp)
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Go back",
                            )
                        }
                    },
                    windowInsets = WindowInsets(
                        top = WindowInsets.systemBars.asPaddingValues().calculateTopPadding() + 8.dp,
                        bottom = 12.dp
                    ),
                )
            },
        ) { paddingValues ->
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                items(items = state.value.tasks, key = {item -> item.id!!}){ item ->
                    TaskItem(item,state.value.text, navController)
                }
            }
        }
    }
}

@Composable
private fun TaskItem(task: Task, substring:String, navController: NavHostController) {
    OutlinedCard(
        onClick = { navController.navigate("editTask/${task.id}") },
        modifier = Modifier.padding(horizontal = 12.dp)
    ) {
        val title: AnnotatedString = createAnnotatedScreen(task.title, substring)
        val description: AnnotatedString = createAnnotatedScreen(task.description, substring)
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Título",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(12.dp)
            )
            Text(
                text = title,
                fontSize = 14.sp,
                modifier = Modifier.padding(12.dp)
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Descrição",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(
                    start = 12.dp, end = 12.dp, bottom = 12.dp
                )
            )
            Text(
                text = description,
                textAlign = TextAlign.Justify,
                fontSize = 14.sp,
                modifier = Modifier.padding(
                    start = 12.dp, end = 12.dp, bottom = 12.dp
                )
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Expira em",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(
                    start = 12.dp, end = 12.dp, bottom = 12.dp
                )
            )
            Text(
                text = task.expirationDate.toBrazilianDateFormat(),
                textAlign = TextAlign.Justify,
                fontSize = 14.sp,
                modifier = Modifier.padding(
                    start = 12.dp, end = 12.dp, bottom = 12.dp
                )
            )
        }
    }
}

private fun createAnnotatedScreen(
    text:String,
    subtext:String
) = buildAnnotatedString {
    val startIndex = text.indexOf(string = subtext, ignoreCase = true)
    if (startIndex >= 0) {
        append(text.substring(0, startIndex))
        withStyle(style = SpanStyle(background = Color.Yellow)) {
            append(subtext)
        }
        append(text.substring(startIndex + subtext.length))

    } else {
        append(text)
    }
}