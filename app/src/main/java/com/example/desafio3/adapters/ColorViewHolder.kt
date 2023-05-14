package com.example.desafio3.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio3.databinding.CardBaseBinding

class ColorViewHolder (view: View): RecyclerView.ViewHolder(view) {
    private val binding = CardBaseBinding.bind(view)

    fun render(data: Any){
        binding.tvDataName.text = data.toString()
    }
}