package ru.soldatov.android.todolist.presentation

import android.app.Application
import ru.soldatov.android.todolist.di.DaggerApplicationComponent

class ToDoApplication : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}