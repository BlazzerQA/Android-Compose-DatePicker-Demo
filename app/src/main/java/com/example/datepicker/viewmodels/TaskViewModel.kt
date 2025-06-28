package com.example.datepicker.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.datepicker.data.Tasks
import com.example.datepicker.repository.TasksRepository
import kotlinx.coroutines.launch

class TaskViewModel(private val repository: TasksRepository) : ViewModel() {

    val tasks = repository.allTasks

    fun addTask(title : String, date : Long? = null) = viewModelScope.launch {
        if (title.isNotBlank()) {
            repository.addTask(Tasks(title = title, date = date))
        }
    }

    fun deleteTask(task : Tasks) = viewModelScope.launch {
        repository.deleteTask(task)
    }




}