package ru.soldatov.android.todolist.domain

import javax.inject.Inject

class AddToDoItemUseCase @Inject constructor(
    private val repository: ToDoListRepository
) {

    operator fun invoke(toDoItem: ToDoItem) {
        repository.addToDoItem(toDoItem)
    }
}