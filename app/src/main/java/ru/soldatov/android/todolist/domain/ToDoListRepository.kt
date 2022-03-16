package ru.soldatov.android.todolist.domain

import androidx.lifecycle.LiveData

interface ToDoListRepository {

    fun addToDoItem(toDoItem: ToDoItem)

    fun deleteToDoItem(toDoItem: ToDoItem)

    fun editToDoItem(toDoItem: ToDoItem)

    fun getToDoItem(toDoItemId: String): ToDoItem

    fun getToDoList(): LiveData<List<ToDoItem>>
}