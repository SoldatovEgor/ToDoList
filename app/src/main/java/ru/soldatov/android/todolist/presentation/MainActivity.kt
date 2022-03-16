package ru.soldatov.android.todolist.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.soldatov.android.todolist.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}