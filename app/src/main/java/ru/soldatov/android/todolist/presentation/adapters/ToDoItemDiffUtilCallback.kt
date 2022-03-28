package ru.soldatov.android.todolist.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import ru.soldatov.android.todolist.domain.ToDoItem

class ToDoItemDiffUtilCallback : DiffUtil.ItemCallback<ToDoItem>() {

    override fun areItemsTheSame(oldItem: ToDoItem, newItem: ToDoItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ToDoItem, newItem: ToDoItem): Boolean {
        return oldItem == newItem
    }
}