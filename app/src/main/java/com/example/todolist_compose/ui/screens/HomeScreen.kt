package com.example.todolist_compose.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolist_compose.localNavController
import com.example.todolist_compose.ui.components.AppBottomBar
import com.example.todolist_compose.ui.components.HomeTopBar
import com.example.todolist_compose.ui.components.TripleSwitch
import com.example.todolist_compose.ui.theme.BabyBlue
import com.example.todolist_compose.ui.theme.Black
import com.example.todolist_compose.ui.theme.DarkGray
import com.example.todolist_compose.ui.theme.Secondary
import com.example.todolist_compose.ui.theme.ToDOListComposeTheme

@Preview(showBackground = true)
@Composable
fun HomeScreen(modifier: Modifier = Modifier){
    val navController = localNavController.current
    ToDOListComposeTheme{
        Scaffold(
            topBar = {
                HomeTopBar(modifier.padding(horizontal = 12.dp))
            },
            bottomBar = {
                AppBottomBar(text = "Create task"
                ) { navController.navigate("newTask") }
            }
        ) { paddingValues ->
            Column(
                modifier
                    .padding(paddingValues)
                    .padding(horizontal = 12.dp)
            ) {
                HomeBody(itens = listOf(
                    "Fazer limpeza da casa",
                    "Fazer limpeza da casa",
                    "Fazer limpeza da casa",
                    "Fazer limpeza da casa",
                    "Fazer limpeza da casa",
                    "Fazer limpeza da casa",
                    "Fazer limpeza da casa",
                    "Fazer limpeza da casa",
                    "Fazer limpeza da casa",
                    "Fazer limpeza da casa"
                ))
            }
        }
    }
}

@Composable
fun HomeBody(modifier: Modifier = Modifier, itens:List<String> = emptyList()){
    TripleSwitch()
    if (itens.isEmpty()){
        EmptyListMessage(text = "É aqui que você encontrará seus projetos finalizados.")
    }
    else {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(vertical = 12.dp)
        ) {
            item {
                Column(
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                        .padding(start = 4.dp)
                ) {
                    Text(
                        text = "Lista",
                        color = Black,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = "Itens marcados como concluídos",
                        color = DarkGray
                    )
                }
            }
            items(itens){ item ->
                OutlinedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = item,
                        fontSize = 14.sp,
                        color = Black,
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }
        }
    }

}

@Composable
fun EmptyListMessage(text:String, modifier:Modifier = Modifier){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Secondary
            ),
            modifier = Modifier
                .padding(20.dp)
                .size(100.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.List,
                contentDescription = null,
                tint = BabyBlue,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(30.dp)
            )
        }
        Text(
            text = "Nada aqui. Por agora.",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            text = text,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 20.dp)
        )
        Button(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "Criar uma task")
        }
    }

}