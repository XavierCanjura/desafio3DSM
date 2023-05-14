package com.example.desafio3.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio3.R

class ColorAdapter(private var colorList: List<Any>): RecyclerView.Adapter<ColorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ColorViewHolder(layoutInflater.inflate(R.layout.card_base, parent, false))
    }

    override fun getItemCount(): Int = colorList.size

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        val marca = colorList[position]
        holder.render(marca)
    }
}