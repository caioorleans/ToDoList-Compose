package com.example.todolist_compose

import android.app.Application
import com.example.todolist_compose.di.appModule
import com.example.todolist_compose.di.storageModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class Application: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@Application)
            modules(appModule, storageModule)
        }
    }
}