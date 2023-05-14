package com.example.desafio3.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio3.R

class UsuarioAdapter(private var usuarioList: List<Any>): RecyclerView.Adapter<UsuarioViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuarioViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return UsuarioViewHolder(layoutInflater.inflate(R.layout.card_base, parent, false))
    }

    override fun getItemCount(): Int = usuarioList.size

    override fun onBindViewHolder(holder: UsuarioViewHolder, position: Int) {
        val marca = usuarioList[position]
        holder.render(marca as ArrayList<String>)
    }
}