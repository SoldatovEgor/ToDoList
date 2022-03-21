package ru.soldatov.android.todolist.data.database

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

interface ToDoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addToDoItem(toDoItemDbModel: ToDoItemDbModel)

    @Query("DELETE FROM to_do_table WHERE id=:toDoItemId")
    fun deleteToDoItem(toDoItemId: Int)

    @Query("SELECT * FROM to_do_table WHERE id=:toDoItemId LIMIT 1")
    fun getToDoItem(toDoItemId: Int): ToDoItemDbModel

    @Query("SELECT * FROM to_do_table")
    fun getToDoList(): LiveData<List<ToDoItemDbModel>>
}