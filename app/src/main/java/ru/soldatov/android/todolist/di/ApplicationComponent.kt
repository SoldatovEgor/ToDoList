package ru.soldatov.android.todolist.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import ru.soldatov.android.todolist.presentation.MainActivity

@ApplicationScope
@Component(
    modules = [
        DataModule::class
    ]
)
interface ApplicationComponent {

    fun inject(activity: MainActivity)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}