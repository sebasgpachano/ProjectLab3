package com.example.sprint3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sprint3.databinding.ItemRickBinding
import com.squareup.picasso.Picasso

class RickAdapter(val rickList: List<RickMortyModel>) : RecyclerView.Adapter<RickViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RickViewHolder {
        val binding = ItemRickBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RickViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RickViewHolder, position: Int) {
        val item = rickList[position]
        Picasso.get().load(item.image).into(holder.binding.ivCharacter)
        holder.binding.tvName.text = item.name
    }

    override fun getItemCount(): Int {
        return rickList.size
    }

}