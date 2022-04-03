package ru.soldatov.android.todolist.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding
import ru.soldatov.android.todolist.databinding.ItemTaskActiveBinding
import ru.soldatov.android.todolist.domain.ToDoItem

class ToDoItemAdapter : ListAdapter<ToDoItem, ToDoItemViewHolder>(ToDoItemDiffUtilCallback()) {

    var toDoItemClickListener: ((ToDoItem) -> Unit)? = null
    var toDoItemClickListenerDone: ((ToDoItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoItemViewHolder {

        val binding = ItemTaskActiveBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ToDoItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ToDoItemViewHolder, position: Int) {

        val toDoItem = getItem(position)
        val binding = holder.binding

        binding.labelTask.text = toDoItem.taskName
        binding.checkboxDone.isChecked = toDoItem.isDone

        binding.root.setOnClickListener {
            toDoItemClickListener?.invoke(toDoItem)
        }

        binding.checkboxDone.setOnClickListener {
            toDoItemClickListenerDone?.invoke(toDoItem)
        }
    }
}
