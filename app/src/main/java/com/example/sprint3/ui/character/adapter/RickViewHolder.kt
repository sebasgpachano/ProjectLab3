package com.example.sprint3.ui.character.adapter


import androidx.recyclerview.widget.RecyclerView
import com.example.sprint3.data.repository.remote.response.characters.RickMortyModel
import com.example.sprint3.databinding.ItemRickBinding
import com.squareup.picasso.Picasso

class RickViewHolder(
    val binding: ItemRickBinding,
    private val listener: RickAdapter.OnItemClickListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: RickMortyModel) {
        Picasso.get().load(item.image).into(binding.ivCharacter)
        binding.tvName.text = item.name
        binding.container.setOnClickListener {
            listener.onItemClick(item.id)
        }
    }

}