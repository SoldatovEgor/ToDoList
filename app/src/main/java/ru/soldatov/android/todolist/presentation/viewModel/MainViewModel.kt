package ru.soldatov.android.todolist.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.soldatov.android.todolist.domain.ToDoItem
import ru.soldatov.android.todolist.domain.useCase.DeleteToDoItemUseCase
import ru.soldatov.android.todolist.domain.useCase.EditToDoItemUseCase
import ru.soldatov.android.todolist.domain.useCase.GetToDoItemUseCase
import ru.soldatov.android.todolist.domain.useCase.GetToDoListUseCase
import javax.inject.Inject

class MainViewModel @Inject constructor(
    getToDoListUseCase: GetToDoListUseCase,
    private val deleteToDoItemUseCase: DeleteToDoItemUseCase,
    private val editToDoItemUseCase: EditToDoItemUseCase
): ViewModel() {

    val toDoList = getToDoListUseCase()

    fun deleteToDoItem(toDoItem: ToDoItem) {
        viewModelScope.launch {
            deleteToDoItemUseCase(toDoItem)
        }
    }

    fun changeIsDone(toDoItem: ToDoItem) {
        viewModelScope.launch {
            val toDoItemCopy = toDoItem.copy(isDone = !toDoItem.isDone)
            editToDoItemUseCase(toDoItemCopy)
        }
    }
}
