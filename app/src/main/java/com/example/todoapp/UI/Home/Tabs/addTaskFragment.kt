package com.example.todoapp.UI.Home.Tabs

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.todoapp.DataBase.MyDataBase
import com.example.todoapp.DataBase.Task
import com.example.todoapp.databinding.FragmentAddTaskBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import java.util.Calendar

class addTaskFragment : BottomSheetDialogFragment() {
    lateinit var viewBinding : FragmentAddTaskBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentAddTaskBinding.inflate(
            inflater ,
            container ,
            false
        )
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.addTaskBtn
            .setOnClickListener{
                createTask()

        }
        viewBinding.DateContainer.setOnClickListener {
            showDatePickerDialog()
        }
    }
    val calendar = Calendar.getInstance()
    private fun showDatePickerDialog() {
        context?.let {
            val dialog = DatePickerDialog(it)
            dialog.setOnDateSetListener { datePicker, day, month, year ->
                viewBinding.TaskDate.setText(
                    "$day-$month-$year"
                )
                calendar.set(year,month,day)
                calendar.set(Calendar.HOUR_OF_DAY , 0)
                calendar.set(Calendar.MINUTE,0)
                calendar.set(Calendar.SECOND,0)
                calendar.set(Calendar.MILLISECOND,0)
            }
            dialog.show()
        }



    }

    private fun valid(): Boolean {
        var isValid = true
        if(viewBinding.TaskTitle.text.toString().isNullOrBlank() ){
            viewBinding.TaskNameContainer.error = "Enter Task Title"
            isValid = false
        }
        else{
            viewBinding.TaskNameContainer.error = null
        }
        if(viewBinding.TaskDescription.text.toString().isNullOrBlank() ){
            viewBinding.TaskDescriptionContainer.error = "Enter Task Description"
            isValid = false

        }
        else{
            viewBinding.TaskNameContainer.error = null

        }
        if(viewBinding.TaskDate.text.toString().isNullOrBlank()){
            viewBinding.DateContainer.error = "Please Enter Task Date"
            isValid = false

        }
        else{
            viewBinding.DateContainer.error = null
        }
        return isValid
    }

    private fun createTask() {
        if(!valid()){
            return
        }
        val task = Task(
            title = viewBinding.TaskTitle.text.toString()
            , description = viewBinding.TaskDescription.text.toString()

        )
        MyDataBase.getInstance(requireContext())
            .tasksDao().insertTask(task)
        dismiss()
        Snackbar.make(viewBinding.root , "Task added Successfully" , Snackbar.LENGTH_SHORT).show()


    }
}