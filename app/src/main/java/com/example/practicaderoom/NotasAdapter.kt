package com.example.practicaderoom

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practicaderoom.databinding.ItemListBinding

class NotasAdapter(private var notas: MutableList<NotasEntity>,
                   private var listener:OnClickListener):
    RecyclerView.Adapter<NotasAdapter.ViewHolder>() {


    private lateinit var mContext:Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context

        val view = LayoutInflater.from(mContext).inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val nota = notas[position]
        //Funcionalidad para recuperar el nombre y si la nota es favorita
        with(holder){
            setListener(nota)
            mBinding.tvItem.text = nota.name
            mBinding.cbFavorite.isChecked = nota.isFavorite
        }
    }

    override fun getItemCount(): Int = notas.size

    fun add(notasEntity: NotasEntity){
        notas.add(notasEntity)
        notifyDataSetChanged()
    }

    fun setNotas(notas: MutableList<NotasEntity>){
        this.notas = notas
        notifyDataSetChanged()
    }

    fun update(notasEntity: NotasEntity){
        val index = notas.indexOf(notasEntity)
        if (index != -1){
            notas.set(index,notasEntity)
            notifyItemChanged(index)
        }
    }

    fun delete(notasEntity: NotasEntity){
        val index = notas.indexOf(notasEntity)
        if (index != -1){
            notas.removeAt(index)
            notifyItemRemoved(index)
        }
    }



    inner class ViewHolder(view:View):RecyclerView.ViewHolder(view) {
        val mBinding = ItemListBinding.bind(view)

        fun setListener(notasEntity: NotasEntity){
            with(mBinding.root){
                setOnClickListener { listener.onClick(notasEntity) }
                setOnLongClickListener { listener.onDeleteNota(notasEntity)
                true}
            }
            mBinding.cbFavorite.setOnClickListener {
                listener.onFavoriteNota(notasEntity)
            }
        }
    }
}