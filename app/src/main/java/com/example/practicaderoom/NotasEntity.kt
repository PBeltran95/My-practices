package com.example.practicaderoom

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "NotasEntity")
data class NotasEntity(
    @PrimaryKey(autoGenerate = true) var id:Long = 0,
    var name:String = "",
    var imgUrl:String = "",
    var isFavorite:Boolean = false
)
