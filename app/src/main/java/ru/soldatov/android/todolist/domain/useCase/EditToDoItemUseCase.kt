package ru.soldatov.android.todolist.domain.useCase

import ru.soldatov.android.todolist.domain.ToDoItem
import ru.soldatov.android.todolist.domain.repository.ToDoListRepository
import javax.inject.Inject

class EditToDoItemUseCase @Inject constructor(
    private val repository: ToDoListRepository
) {

    operator fun invoke(toDoItem: ToDoItem) {
        repository.editToDoItem(toDoItem)
    }
}