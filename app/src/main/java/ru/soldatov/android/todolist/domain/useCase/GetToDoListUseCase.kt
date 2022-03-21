package ru.soldatov.android.todolist.domain.useCase

import ru.soldatov.android.todolist.domain.repository.ToDoListRepository
import javax.inject.Inject

class GetToDoListUseCase @Inject constructor(
    private val repository: ToDoListRepository
) {

    operator fun invoke() = repository.getToDoList()
}