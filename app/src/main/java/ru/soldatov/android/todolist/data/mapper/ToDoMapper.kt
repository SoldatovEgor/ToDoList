package ru.soldatov.android.todolist.data.mapper

import ru.soldatov.android.todolist.data.database.ToDoItemDbModel
import ru.soldatov.android.todolist.domain.ToDoItem
import javax.inject.Inject

class ToDoMapper @Inject constructor(){

    fun mapEntityToDbModel(toDoItem: ToDoItem): ToDoItemDbModel = ToDoItemDbModel(
        id = toDoItem.id,
        taskName = toDoItem.taskName,
        taskDescription = toDoItem.taskDescription,
        isDone = toDoItem.isDone
    )

    fun mapDbModelToEntity(toDoItemDbModel: ToDoItemDbModel): ToDoItem = ToDoItem(
        id = toDoItemDbModel.id,
        taskName = toDoItemDbModel.taskName,
        taskDescription = toDoItemDbModel.taskDescription,
        isDone = toDoItemDbModel.isDone
    )

    fun mapListDbModelToListEntity(list: List<ToDoItemDbModel>) = list.map {
        mapDbModelToEntity(it)
    }

}