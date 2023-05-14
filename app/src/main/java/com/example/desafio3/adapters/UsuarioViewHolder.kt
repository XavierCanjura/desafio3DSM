package com.example.desafio3.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio3.databinding.CardBaseBinding

class UsuarioViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = CardBaseBinding.bind(view)

    fun render(data: ArrayList<String>){
        binding.tvDataName.text = data[1] + " " + data[2]
    }
}