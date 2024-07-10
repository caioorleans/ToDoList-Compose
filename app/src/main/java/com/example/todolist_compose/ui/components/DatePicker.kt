package com.example.todolist_compose.ui.components

import android.app.TimePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerColors
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todolist_compose.events.CreateTaskEvents
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerComponent(expirationDate:Long, onEvent:(CreateTaskEvents)->Unit){
    var showDateDialog by remember {
        mutableStateOf(false)
    }
    val datePickerState = rememberDatePickerState()
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "Data de término",
            modifier = Modifier.padding(start = 8.dp)
        )
        OutlinedTextField(
            value = expirationDate.toBrazilianDateFormat(),
            onValueChange = { },
            singleLine = true,
            readOnly = true,
            textStyle = TextStyle(
                textAlign = TextAlign.End
            ),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Filled.DateRange,
                    contentDescription = "Select date",
                    modifier = Modifier
                        .clickable { showDateDialog = true }
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                disabledBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
            )
        )
    }
    if(showDateDialog){
        DatePickerDialog(
            onDismissRequest = { showDateDialog = false },
            confirmButton = {
                Button(
                    onClick = {
                        datePickerState
                            .selectedDateMillis?.let { millis ->
                                onEvent(CreateTaskEvents.setExpirationDate(millis))
                            }
                        showDateDialog = false
                    }) {
                    Text(text = "Escolher data")
                }
            }) {
            DatePicker(
                state = datePickerState,
                colors = DatePickerDefaults.colors()
            )
        }
    }
    HorizontalDivider()
}

fun Long.toBrazilianDateFormat(
    pattern: String = "dd/MM/yyyy"
): String {
    val date = Date(this)
    val formatter = SimpleDateFormat(
        pattern, Locale("pt-br")
    ).apply {
        timeZone = TimeZone.getTimeZone("GMT")
    }
    return formatter.format(date)
}