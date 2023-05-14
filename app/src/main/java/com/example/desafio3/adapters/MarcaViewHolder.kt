package com.example.desafio3.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.desafio3.databinding.CardBaseBinding


class MarcaViewHolder(view: View): ViewHolder(view) {
    private val binding = CardBaseBinding.bind(view)

    fun render(data: ArrayList<String>, onClickListener: (ArrayList<String>) -> Unit){
        binding.tvDataName.text = data[1]
        binding.btnEdit.setOnClickListener {
            var info = ArrayList<String>()
            info.add("Editar")
            info.add(data[0])
            onClickListener(info)
        }
        binding.btnDelete.setOnClickListener {
            var info = ArrayList<String>()
            info.add("Eliminar")
            info.add(data[0])
            onClickListener(info) }
    }
}