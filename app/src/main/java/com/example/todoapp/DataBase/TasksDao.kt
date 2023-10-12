package com.example.todoapp.DataBase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TasksDao {
    @Insert
    fun insertTask (task : Task)
    @Update
    fun updateTask (task: Task)
    @Delete
    fun deleteTask (task: Task)
    @Query("Select dateTime from tasks where dateTime = :dateTime")
    fun getTaskByDate (dateTime : Long):List<Task>
    @Query("Select * from tasks")
    fun getAllTasks():List<Task>


}