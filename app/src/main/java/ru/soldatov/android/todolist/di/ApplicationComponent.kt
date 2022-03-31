package ru.soldatov.android.todolist.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import ru.soldatov.android.todolist.presentation.ToDoDetailFragment
import ru.soldatov.android.todolist.presentation.ToDoListFragment

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    fun inject(fragment: ToDoListFragment)

    fun inject(fragment: ToDoDetailFragment)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}