package com.example.todolist_compose.di

import androidx.room.Room
import com.example.todolist_compose.database.TaskDatabase
import com.example.todolist_compose.repository.TaskRepository
import com.example.todolist_compose.ui.viewModel.UpsertTaskViewModel
import com.example.todolist_compose.ui.viewModel.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    singleOf(::TaskRepository)
    viewModelOf(::HomeViewModel)
    viewModelOf(::UpsertTaskViewModel)
}

val storageModule = module{
    single {
        Room.databaseBuilder(
            androidContext(),
            TaskDatabase::class.java,
            "task.db"
        ).fallbackToDestructiveMigration().build()
    }
    single { get<TaskDatabase>().dao }
}