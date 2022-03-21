package ru.soldatov.android.todolist.domain.useCase

import ru.soldatov.android.todolist.domain.repository.ToDoListRepository
import javax.inject.Inject

class GetToDoItemUseCase @Inject constructor(
    private val repository: ToDoListRepository
) {

    suspend operator fun invoke(toDoItemId: Int) {
        repository.getToDoItem(toDoItemId)
    }
}