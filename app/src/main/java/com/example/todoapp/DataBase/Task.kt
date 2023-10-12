package com.example.todoapp.DataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("Tasks")
data class Task(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    var id : Int ?= null ,
    var title : String ?= null ,
    var description : String ?= null ,
    var dateTime : Long ?= null ,
    var isDone : Boolean ?= null

)
