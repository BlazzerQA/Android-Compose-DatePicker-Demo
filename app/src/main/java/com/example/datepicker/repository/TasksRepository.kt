package com.example.datepicker.repository

import com.example.datepicker.data.Tasks
import com.example.datepicker.data.TasksDao
import kotlinx.coroutines.flow.Flow


class TasksRepository(private val tasksDao: TasksDao) {

    val allTasks : Flow<List<Tasks>> = tasksDao.getAll()

    suspend fun addTask (task: Tasks) {
        tasksDao.insert(task)
    }

    suspend fun deleteTask (task : Tasks) {
        tasksDao.delete(task)
    }
}