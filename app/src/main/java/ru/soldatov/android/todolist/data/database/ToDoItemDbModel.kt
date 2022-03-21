package ru.soldatov.android.todolist.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "to_do_table")
data class ToDoItemDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val taskName: String,
    val taskDescription: String,
    val isDone: Boolean,
)