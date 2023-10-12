package com.example.todoapp.UI.Home.Tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todoapp.Adapters.TasksAdapter
import com.example.todoapp.DataBase.MyDataBase
import com.example.todoapp.DataBase.Task
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    lateinit var viewBinding : FragmentHomeBinding
    lateinit var taskAdapter : TasksAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       viewBinding = FragmentHomeBinding.inflate(inflater ,container , false)
        return viewBinding.root
    }

    override fun onResume() {
        super.onResume()
        loadTasks()
    }

    override fun onStart() {
        super.onStart()
        loadTasks()

    }

    private fun loadTasks() {
       val tasks =  MyDataBase.getInstance(requireContext())
            .tasksDao().getAllTasks()
        taskAdapter.bindTasks(tasks)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        taskAdapter = TasksAdapter(null)
        viewBinding.TasksRv.adapter = taskAdapter
    }


}