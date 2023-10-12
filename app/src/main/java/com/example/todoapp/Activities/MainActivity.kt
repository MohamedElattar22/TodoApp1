package com.example.todoapp.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Slide
import androidx.transition.TransitionManager
import com.example.todoapp.DataBase.MyDataBase
import com.example.todoapp.DataBase.TasksDao
import com.example.todoapp.R
import com.example.todoapp.UI.Home.Tabs.HomeFragment
import com.example.todoapp.UI.Home.Tabs.SettingFragment
import com.example.todoapp.UI.Home.Tabs.addTaskFragment
import com.example.todoapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import java.time.chrono.HijrahChronology

class MainActivity : AppCompatActivity() {
    lateinit var bottomNavigationView: BottomNavigationView
    lateinit var viewBinding : ActivityMainBinding
    lateinit var Task1 : TasksDao
    lateinit var DP: MyDataBase
    lateinit var RV : RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(
            layoutInflater
        )
        setContentView(viewBinding.root)
        pushFragment(HomeFragment())

        bottomNavigationView = findViewById(R.id.bottomNavBar)
//        bottomNavigationView.setOnItemReselectedListener { item ->
//            when(item.itemId) {
//                R.id.taskFragment -> {
//                    pushFragment(HomeFragment())
//                }
//                R.id.SettingFragment -> {
//                    pushFragment(SettingFragment())
//                }
//            }
//        }
        var tasksfrag = HomeFragment()
        bottomNavigationView.setOnItemSelectedListener {
            if(it.itemId == R.id.taskFragment){
               pushFragment(HomeFragment())
            }
            else if(it.itemId == R.id.SettingFragment) {
                pushFragment(SettingFragment())
            }
            return@setOnItemSelectedListener true
        }
        viewBinding.addTask.setOnClickListener {
            showAddTaskBottomSheet()

        }

    }

    private fun showAddTaskBottomSheet() {
        val addTaskSheet = addTaskFragment()
        addTaskSheet.show(supportFragmentManager , "")


    }

    private fun pushFragment(fragment: Fragment) {

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
            .addToBackStack(null)
            .commit()

    }
}