package com.example.sprint3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RickAdapter(val rickList: List<RickMortyModel>) : RecyclerView.Adapter<RickViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RickViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return RickViewHolder(layoutInflater.inflate(R.layout.item_rick, parent, false))
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RickViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

}