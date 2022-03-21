package ru.soldatov.android.todolist.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import ru.soldatov.android.todolist.data.database.ToDoDao
import ru.soldatov.android.todolist.data.mapper.ToDoMapper
import ru.soldatov.android.todolist.domain.ToDoItem
import ru.soldatov.android.todolist.domain.repository.ToDoListRepository
import javax.inject.Inject

class ToDoListRepositoryImpl @Inject constructor(
    private val toDoDao: ToDoDao,
    private val mapper: ToDoMapper
) : ToDoListRepository {

    override fun addToDoItem(toDoItem: ToDoItem) {
        toDoDao.addToDoItem(mapper.mapEntityToDbModel(toDoItem))
    }

    override fun deleteToDoItem(toDoItemId: Int) {
        toDoDao.deleteToDoItem(toDoItemId)
    }

    override fun editToDoItem(toDoItem: ToDoItem) {
        toDoDao.addToDoItem(mapper.mapEntityToDbModel(toDoItem))
    }

    override fun getToDoItem(toDoItemId: Int): ToDoItem {
        val dbModel = toDoDao.getToDoItem(toDoItemId)
        return mapper.mapDbModelToEntity(dbModel)
    }

    override fun getToDoList(): LiveData<List<ToDoItem>> = Transformations.map(
        toDoDao.getToDoList()
    ) {
        mapper.mapListDbModelToListEntity(it)
    }
}