package com.example.datepicker.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Tasks ::class], version = 1)
abstract class AppDataBase : RoomDatabase () {
    abstract fun tasksDao() : TasksDao
}