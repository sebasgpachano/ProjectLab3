package com.example.sprint3.ui.character.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sprint3.data.domain.model.character.CharacterModel
import com.example.sprint3.databinding.ItemRickBinding

class RickAdapter(
    val rickList: ArrayList<CharacterModel>,
    private val listener: OnItemClickListener
) :
    RecyclerView.Adapter<RickViewHolder>() {

    fun interface OnItemClickListener {
        fun onItemClick(item: Int)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RickViewHolder {
        val binding = ItemRickBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RickViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: RickViewHolder, position: Int) {
        val item = rickList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return rickList.size
    }

    fun refreshData(names: List<CharacterModel>) {
        rickList.clear()
        rickList.addAll(names)
        notifyDataSetChanged()
    }

}