package ru.soldatov.android.todolist.data.repository

import androidx.lifecycle.LiveData
import ru.soldatov.android.todolist.domain.ToDoItem
import ru.soldatov.android.todolist.domain.ToDoListRepository

class ToDoListRepositoryImpl() : ToDoListRepository {

    override fun addToDoItem(toDoItem: ToDoItem) {
        TODO("Not yet implemented")
    }

    override fun deleteToDoItem(toDoItem: ToDoItem) {
        TODO("Not yet implemented")
    }

    override fun editToDoItem(toDoItem: ToDoItem) {
        TODO("Not yet implemented")
    }

    override fun getToDoItem(toDoItemId: String): ToDoItem {
        TODO("Not yet implemented")
    }

    override fun getToDoList(): LiveData<List<ToDoItem>> {
        TODO("Not yet implemented")
    }
}