package ru.soldatov.android.todolist.domain

import javax.inject.Inject

class GetToDoListUseCase @Inject constructor(
    private val repository: ToDoListRepository
) {

    operator fun invoke() = repository.getToDoList()
}