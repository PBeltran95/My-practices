package com.example.practicaderoom

import android.app.Application
import androidx.room.Room

class NotaApplication:Application() {

    companion object{
        lateinit var database: NotasDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room
            .databaseBuilder(this, NotasDatabase::class.java, "NotasDatabase")
            .build()
    }
}