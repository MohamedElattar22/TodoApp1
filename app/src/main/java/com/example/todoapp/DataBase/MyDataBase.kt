package com.example.todoapp.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Task::class] , version = 4 , exportSchema = true )
abstract class MyDataBase : RoomDatabase() {
   abstract fun tasksDao() : TasksDao

   companion object {
      private var instance : MyDataBase?= null //

       fun getInstance(context : Context): MyDataBase {
           if(instance == null){
               instance = Room.databaseBuilder(
                   context.applicationContext , MyDataBase::class.java ,"tasksDataBase"
               )
                   .allowMainThreadQueries()
                   .fallbackToDestructiveMigration()
                   .build()
           }
           return instance!!
       }
   }

}