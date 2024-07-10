package com.example.todolist_compose.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun OtherTopAppBar(
    modifier: Modifier = Modifier,
    goBackAction:()->Unit = {},
    clearAction: ()->Unit = {}
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Projeto",
                fontWeight = FontWeight.Bold
            )
        },
        actions = { Text(
            text = "Limpar",
            modifier = Modifier.clickable { clearAction() }
        ) },
        navigationIcon = {
            Text(
                text = "Voltar",
                modifier = Modifier.clickable { goBackAction() }
            )
        },
        modifier = modifier
            .padding(bottom = 12.dp)
    )
}