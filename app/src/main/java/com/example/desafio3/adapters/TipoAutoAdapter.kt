package com.example.desafio3.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio3.R

class TipoAutoAdapter(
    private var tipoAutoList: List<Any>,
    private var onClickListener: (ArrayList<String>) -> Unit
    ): RecyclerView.Adapter<TipoAutoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TipoAutoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TipoAutoViewHolder(layoutInflater.inflate(R.layout.card_base, parent, false))
    }

    override fun getItemCount(): Int = tipoAutoList.size

    override fun onBindViewHolder(holder: TipoAutoViewHolder, position: Int) {
        val tipoAuto = tipoAutoList[position]
        holder.render(tipoAuto as ArrayList<String>, onClickListener)
    }
}