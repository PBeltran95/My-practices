package com.example.dogphotos

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.dogphotos.databinding.ItemDogBinding
import com.squareup.picasso.Picasso

class DogViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val mBinding = ItemDogBinding.bind(view)

    fun bind(image:String){
        Picasso.get().load(image).into(mBinding.ivDog)

    }
}