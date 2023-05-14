package com.example.desafio3.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio3.R

class AutoMovilAdapter(private var autoList: List<Any>): RecyclerView.Adapter<AutoMovilViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AutoMovilViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return AutoMovilViewHolder(layoutInflater.inflate(R.layout.card_auto, parent, false))
    }

    override fun getItemCount(): Int = autoList.size

    override fun onBindViewHolder(holder: AutoMovilViewHolder, position: Int) {
        val marca = autoList[position]
        holder.render(marca as ArrayList<String>)
    }
}