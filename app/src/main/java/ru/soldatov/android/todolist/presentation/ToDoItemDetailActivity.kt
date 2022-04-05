package ru.soldatov.android.todolist.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.soldatov.android.todolist.R
import ru.soldatov.android.todolist.domain.ToDoItem

class ToDoItemDetailActivity : AppCompatActivity(), ToDoDetailFragment.OnEditingFinished {

    private var screenMode = UNDEFINED_MODE
    private var taskItemId = ToDoItem.UNDEFINED_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_task)
        parseIntent()
        launchFragmentDetail()
        setupToolbar()
    }

    private fun parseIntent() {
        if (!intent.hasExtra(EXTRA_SCREEN_MODE))
            throw RuntimeException("Parameter MODE not passed")
        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
        if (mode != MODE_ADD && mode != MODE_EDIT)
            throw RuntimeException("MODE $mode not find")
        screenMode = mode
        if (screenMode == MODE_EDIT) {
            if (!intent.hasExtra(EXTRA_TASK_ID))
                throw RuntimeException("Parameter ID not passed")
            taskItemId = intent.getIntExtra(EXTRA_TASK_ID, ToDoItem.UNDEFINED_ID)
        }
    }

    private fun launchFragmentDetail() {
        val fragment = when(screenMode) {
            MODE_ADD -> {
                ToDoDetailFragment.newInstanceAdd()
            }
            MODE_EDIT -> {
                ToDoDetailFragment.newInstanceEdit(taskItemId)
            }
            else -> throw RuntimeException("Not find mode: $screenMode")
        }
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    private fun setupToolbar() {
        setSupportActionBar(findViewById(R.id.detail_toolbar))
        when(screenMode) {
            MODE_ADD -> supportActionBar?.setTitle(R.string.add_new_to_do)
            MODE_EDIT -> supportActionBar?.setTitle(R.string.edit_to_do)
        }
    }

    companion object {

        private const val EXTRA_SCREEN_MODE = "extra_mode"
        private const val EXTRA_TASK_ID = "extra_task_id"
        private const val MODE_ADD = "mode_task_add"
        private const val MODE_EDIT = "mode_task_edit"

        private const val UNDEFINED_MODE =""

        fun newIntentAdd(context: Context): Intent {
            val intent = Intent(context, ToDoItemDetailActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
            return intent
        }

        fun newIntentEdit(context: Context, taskId: Int): Intent {
            val intent = Intent(context, ToDoItemDetailActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
            intent.putExtra(EXTRA_TASK_ID, taskId)
            return intent
        }
    }

    override fun onEditingFinished() {
        finish()
    }
}