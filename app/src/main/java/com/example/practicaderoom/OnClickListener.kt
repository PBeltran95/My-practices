package com.example.practicaderoom

interface OnClickListener {
    fun onClick(notasEntity: NotasEntity)

    fun onFavoriteNota(notasEntity: NotasEntity)

    fun onDeleteNota(notasEntity: NotasEntity)
}