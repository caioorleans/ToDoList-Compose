package com.example.todolist_compose.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todolist_compose.ui.theme.Gray
import com.example.todolist_compose.ui.theme.Primary
import com.example.todolist_compose.ui.theme.Secondary

data class NavigationItem(val icon:ImageVector, val text:String)

private val navigationItens = listOf(
    NavigationItem(Icons.Filled.Home, "Home"),
    NavigationItem(Icons.Filled.AddCircle, "Criar"),
    NavigationItem(Icons.Filled.Edit, "Editar"),
    NavigationItem(Icons.Filled.Delete, "Excluir")
)

@Composable
@Preview
fun BottomNavBar(modifier: Modifier = Modifier ){
    var selectedIndex:Int = 0
    NavigationBar(
        containerColor = Color.Transparent,
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = modifier.fillMaxWidth()
        ) {
            navigationItens.forEachIndexed { index, item ->
                BottomNavBarIcon(item, index == selectedIndex)
            }
        }
    }
}

@Composable
fun BottomNavBarIcon(item:NavigationItem, selected:Boolean, modifier: Modifier = Modifier){
    val color = if(selected) Primary else Gray
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            imageVector = item.icon,
            contentDescription = item.text,
            tint = color,
            modifier = modifier.padding(bottom = 8.dp)
        )
        Text(
            text = item.text,
            color = color
        )
    }
}