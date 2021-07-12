package com.example.gatos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.gatos.databinding.ItemGatosBinding
import com.squareup.picasso.Picasso

class CatAdapter(val cats: List<Cats>): RecyclerView.Adapter<CatAdapter.CatHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CatHolder(layoutInflater.inflate(R.layout.item_gatos, parent,false))
    }

    override fun onBindViewHolder(holder: CatHolder, position: Int) {
        holder.render(cats[position])
    }

    override fun getItemCount(): Int = cats.size


    class CatHolder(view: View):RecyclerView.ViewHolder(view){
        val cBinding = ItemGatosBinding.bind(view)

        fun render(cats: Cats){
            cBinding.catsName.text = cats.catName
            cBinding.catsRaze.text = cats.catRaze
            cBinding.catsAge.text = cats.catAge
            Picasso.get().load(cats.image).into(cBinding.ivCats)
            cBinding.clItemCats.setOnClickListener { Toast.makeText(cBinding.clItemCats.context,
                "Has seleccionado a ${cats.catName}", Toast.LENGTH_SHORT).show() }
        }

    }
}