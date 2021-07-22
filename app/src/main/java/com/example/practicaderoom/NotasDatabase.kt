package com.example.practicaderoom

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(NotasEntity::class), version = 1)
abstract class NotasDatabase:RoomDatabase() {
    abstract fun notaDao():NotaDao
}