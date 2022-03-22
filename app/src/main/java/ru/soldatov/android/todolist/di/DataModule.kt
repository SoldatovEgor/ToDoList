package ru.soldatov.android.todolist.di

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.soldatov.android.todolist.data.database.AppDatabase
import ru.soldatov.android.todolist.data.database.ToDoDao
import ru.soldatov.android.todolist.data.repository.ToDoListRepositoryImpl
import ru.soldatov.android.todolist.domain.repository.ToDoListRepository

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindToDoRepository(impl: ToDoListRepositoryImpl): ToDoListRepository

    companion object {

        @ApplicationScope
        @Provides
        fun provideToDoDao(
            application: Application
        ): ToDoDao {
            return AppDatabase.getInstance(application).toDoDao()
        }
    }
}