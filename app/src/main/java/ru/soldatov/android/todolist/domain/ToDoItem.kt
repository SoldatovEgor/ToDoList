package ru.soldatov.android.todolist.domain

import java.util.*

data class ToDoItem (
    val taskName: String,
    val taskDescription: String,
    val isDone: Boolean,
    val id: Int = UNDEFINED_ID
) {

    companion object {
        const val UNDEFINED_ID = 0
    }
}