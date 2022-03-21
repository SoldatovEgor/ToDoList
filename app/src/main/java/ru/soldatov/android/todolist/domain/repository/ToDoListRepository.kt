package ru.soldatov.android.todolist.domain.repository

import androidx.lifecycle.LiveData
import ru.soldatov.android.todolist.domain.ToDoItem

interface ToDoListRepository {

    suspend fun addToDoItem(toDoItem: ToDoItem)

    suspend fun deleteToDoItem(toDoItemId: Int)

    suspend fun editToDoItem(toDoItem: ToDoItem)

    suspend fun getToDoItem(toDoItemId: Int): ToDoItem

    fun getToDoList(): LiveData<List<ToDoItem>>
}