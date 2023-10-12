package com.example.todoapp.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.DataBase.Task
import com.example.todoapp.R
import com.example.todoapp.databinding.ActivityMainBinding
import com.example.todoapp.databinding.TasksLayoutBinding

class TasksAdapter(var tasksList: List<Task>?) : RecyclerView.Adapter<TasksAdapter.ViewHolder>() {
    lateinit var binde : ActivityMainBinding
    class ViewHolder(ItemView:View)  :RecyclerView.ViewHolder(ItemView) {
        val taskTitle : TextView = ItemView.findViewById(R.id.taskTitleTV)
        val taskDiscription : TextView = ItemView.findViewById(R.id.taskDescTV)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tasks_layout , parent , false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item =tasksList?.get(position)
        holder.taskTitle.text = tasksList!![position].title.toString()
        holder.taskDiscription.text = tasksList!![position].description.toString()
    }

    override fun getItemCount(): Int {
        return tasksList?.size?:0
    }

    fun bindTasks(tasks : List<Task>) {
        this.tasksList = tasks
        notifyDataSetChanged()
    }

}