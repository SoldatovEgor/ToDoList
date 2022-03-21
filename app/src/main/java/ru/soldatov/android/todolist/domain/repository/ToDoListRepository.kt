package ru.soldatov.android.todolist.domain.repository

import androidx.lifecycle.LiveData
import ru.soldatov.android.todolist.domain.ToDoItem

interface ToDoListRepository {

    fun addToDoItem(toDoItem: ToDoItem)

    fun deleteToDoItem(toDoItemId: Int)

    fun editToDoItem(toDoItem: ToDoItem)

    fun getToDoItem(toDoItemId: Int): ToDoItem

    fun getToDoList(): LiveData<List<ToDoItem>>
}