package com.example.practicaderoom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practicaderoom.databinding.ActivityMainBinding
import com.example.practicaderoom.databinding.ActivityMainBinding.inflate
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity(), OnClickListener {
    //Bindeamos la vista
    private lateinit var mBinding:ActivityMainBinding

    //LLamamos al adaptador
    private lateinit var mAdapter: NotasAdapter

    //Llamamos al manager del recyclerView
    private lateinit var mGridLayout:RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.btnSave.setOnClickListener {
            val nota = NotasEntity(name = mBinding.etName.text.toString().trim())

            Thread{
                NotaApplication.database.notaDao().addNota(nota)
            }.start()

            mAdapter.add(nota)
        }

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        mAdapter = NotasAdapter(mutableListOf(), this)
        mGridLayout = GridLayoutManager(this,1)
        getStores()
        mBinding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = mGridLayout
            adapter = mAdapter
        }
    }

    private fun getStores() {
        doAsync {
            val notas = NotaApplication.database.notaDao().getAllNotes()
            uiThread { mAdapter.setNotas(notas) }
        }
    }

    override fun onClick(notasEntity: NotasEntity) {
    }

    override fun onFavoriteNota(notasEntity: NotasEntity) {
        notasEntity.isFavorite = !notasEntity.isFavorite
        doAsync {
            NotaApplication.database.notaDao().updateNota(notasEntity)
            uiThread { mAdapter.update(notasEntity) }
        }
    }

    override fun onDeleteNota(notasEntity: NotasEntity) {
        doAsync { NotaApplication.database.notaDao().deleteNota(notasEntity)
        uiThread {
            mAdapter.delete(notasEntity)
            }
        }
    }
}