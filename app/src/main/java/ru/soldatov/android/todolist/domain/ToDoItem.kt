package ru.soldatov.android.todolist.domain

import java.util.*

data class ToDoItem (
    val taskName: String,
    val taskDescription: String,
    val isDone: Boolean,
    val id: String = UUID.randomUUID().toString()
)