package ru.soldatov.android.todolist.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.soldatov.android.todolist.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentFragment =
            supportFragmentManager.findFragmentById(R.id.main_container)

        if (currentFragment == null) {
            val fragment = ToDoListFragment.newInstance()
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, fragment)
                .addToBackStack(null)
                .commit()
        }

    }
}