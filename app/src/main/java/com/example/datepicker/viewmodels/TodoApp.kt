@file:Suppress("UNCHECKED_CAST")

package com.example.datepicker.viewmodels

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.example.datepicker.data.AppDataBase
import com.example.datepicker.repository.TasksRepository
import com.example.datepicker.ui.screens.MainScreen

class TodoApp : Application() {

    val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDataBase ::class.java,
            "todo.db"
        ).build()
    }
    val repository by lazy { TasksRepository(database.tasksDao()) }
}

@Composable
fun TodoEntryPoint() {
    val app = LocalContext.current.applicationContext as TodoApp
    val viewModel : TaskViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create (modelClass : Class<T>) : T {
                return TaskViewModel(app.repository) as T
            }
        }
    )

    MainScreen(viewModel)

}
