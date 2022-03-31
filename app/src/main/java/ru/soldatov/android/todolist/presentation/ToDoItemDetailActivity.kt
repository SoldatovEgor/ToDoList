package ru.soldatov.android.todolist.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.soldatov.android.todolist.R

class ToDoItemDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_task)
        launchFragmentDetail()
    }

    private fun launchFragmentDetail() {
        val fragment = ToDoDetailFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    companion object {

        fun newIntentAdd(context: Context): Intent {
            val intent = Intent(context, ToDoItemDetailActivity::class.java)
            return intent
        }
    }
}