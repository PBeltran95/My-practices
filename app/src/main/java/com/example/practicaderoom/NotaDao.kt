package com.example.practicaderoom

import androidx.room.*

@Dao
interface NotaDao {
    @Query("SELECT * FROM NOTASENTITY")
    fun getAllNotes():MutableList<NotasEntity>


    @Insert
    fun addNota(notasEntity: NotasEntity)

    @Update
    fun updateNota(notasEntity: NotasEntity)

    @Delete
    fun deleteNota(notasEntity: NotasEntity)
}