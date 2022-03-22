package ru.soldatov.android.todolist.domain.useCase

import ru.soldatov.android.todolist.domain.ToDoItem
import ru.soldatov.android.todolist.domain.repository.ToDoListRepository
import javax.inject.Inject

class DeleteToDoItemUseCase @Inject constructor(
    private val repository: ToDoListRepository
){

    suspend operator fun invoke(toDoItem: ToDoItem) {
        repository.deleteToDoItem(toDoItem)
    }
}