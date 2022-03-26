package ru.soldatov.android.todolist.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.soldatov.android.todolist.domain.ToDoItem
import ru.soldatov.android.todolist.domain.useCase.AddToDoItemUseCase
import ru.soldatov.android.todolist.domain.useCase.EditToDoItemUseCase
import ru.soldatov.android.todolist.domain.useCase.GetToDoItemUseCase
import javax.inject.Inject

class ToDoItemViewModel @Inject constructor(
    private val getToDoItemUseCase: GetToDoItemUseCase,
    private val editToDoItemUseCase: EditToDoItemUseCase,
    private val addToDoItemUseCase: AddToDoItemUseCase
): ViewModel() {

    private val _toDoItem = MutableLiveData<ToDoItem>()

    private val _errorInputLabel = MutableLiveData<Boolean>()
    private val _shouldScreenClose = MutableLiveData<Unit>()

    val toDoItem: LiveData<ToDoItem>
        get() = _toDoItem

    val errorInputLabel: LiveData<Boolean>
        get() = _errorInputLabel

    val shouldCloseScreen: LiveData<Unit>
        get() = _shouldScreenClose

    fun getToDoItem(toDoItemId: Int) {
        viewModelScope.launch {
            val toDoItem = getToDoItemUseCase(toDoItemId)
            _toDoItem.value = toDoItem
        }
    }

    fun addToDoItem(inputLabel: String?, inputDescription: String?) {
        val taskLabel = parseLabel(inputLabel)
        val taskDescription = parseDescription(inputDescription)
        val isValidateInput = validateInputLabel(taskLabel)
        if (isValidateInput) {
            viewModelScope.launch {
                val toDoItem = ToDoItem(taskLabel, taskDescription, false)
                addToDoItemUseCase(toDoItem)
                finishWork()
            }
        }
    }

    fun editToDoItem(inputLabel: String?, inputDescription: String?) {
        val taskLabel = parseLabel(inputLabel)
        val taskDescription = parseDescription(inputDescription)
        val isValidateInput = validateInputLabel(taskLabel)
        if (isValidateInput) {
            _toDoItem.value?.let {
                viewModelScope.launch {
                    val toDoItem = it.copy(taskName = taskLabel, taskDescription = taskDescription)
                    editToDoItemUseCase(toDoItem)
                    finishWork()
                }
            }
        }
    }

    private fun parseLabel(inputLabel: String?): String {
        return inputLabel?.trim() ?: ""
    }

    private fun parseDescription(inputDescription: String?): String {
        return inputDescription?.trim() ?: ""
    }

    private fun validateInputLabel(taskLabel: String): Boolean {
        var result = true
        if (taskLabel.isBlank()) {
            _errorInputLabel.value = true
            result = false
        }
        return result
    }

    private fun resetErrorInput() {
        _errorInputLabel.value = false
    }

    private fun finishWork() {
        _shouldScreenClose.value = Unit
    }
}