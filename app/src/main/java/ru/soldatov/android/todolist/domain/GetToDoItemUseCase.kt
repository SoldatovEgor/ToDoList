package ru.soldatov.android.todolist.domain

import javax.inject.Inject

class GetToDoItemUseCase @Inject constructor(
    private val repository: ToDoListRepository
) {

    operator fun invoke(toDoItemId: String) {
        repository.getToDoItem(toDoItemId)
    }
}