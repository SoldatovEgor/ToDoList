package ru.soldatov.android.todolist.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.soldatov.android.todolist.presentation.viewModel.MainViewModel
import ru.soldatov.android.todolist.presentation.viewModel.ToDoItemViewModel

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ToDoItemViewModel::class)
    fun bindToDoItemViewModel(viewModel: ToDoItemViewModel): ViewModel

}
