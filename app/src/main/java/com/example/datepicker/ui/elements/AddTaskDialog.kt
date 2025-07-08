package com.example.datepicker.ui.elements

import android.icu.util.Calendar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale.getDefault

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskDialog(
    onSave: (String, Long?) -> Unit,
    onDismiss: () -> Unit
    ) {

    var taskName by remember { mutableStateOf("") }
    var showDatePicker by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf<Long?>(null) }
    var formatDate by remember { mutableStateOf("") }

    if(showDatePicker) {
        DatePickerDialog(
            onDateSelected = {timestamp ->
                selectedDate = timestamp
                formatDate = SimpleDateFormat("dd.MM.yyyy", getDefault())
                    .format(Date(timestamp))
                showDatePicker = false
            },
            onDismiss = {showDatePicker = false}
        )
    }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {

                TextField(
                    value = taskName,
                    onValueChange = {taskName = it},
                    label = {Text("Название задачи")},
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {showDatePicker = true},
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.Blue
                    )
                ) {
                    Text("Выбрать дату")
                }

                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    //Кнопка 1
                    Button(
                        onClick = { onDismiss }
                    ) {
                        Text("Отмена")
                    }
                    //Кнопка 2
                    Button(
                        onClick = {onSave(taskName,selectedDate)},
                        enabled = taskName.isNotBlank()
                    ) {
                        Text("Cохранить")
                    }

                }

            }
        }
    }








}