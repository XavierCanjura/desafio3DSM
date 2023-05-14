package com.example.desafio3.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.desafio3.R


class MarcaAdapter(
    private var marcaList: List<Any>,
    private var onClickListener: (ArrayList<String>) -> Unit
    ) : Adapter<MarcaViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarcaViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MarcaViewHolder(layoutInflater.inflate(R.layout.card_base, parent, false))
    }

    override fun getItemCount(): Int = marcaList.size

    override fun onBindViewHolder(holder: MarcaViewHolder, position: Int) {
        val marca = marcaList[position]
        holder.render(marca as ArrayList<String>, onClickListener)
    }
}