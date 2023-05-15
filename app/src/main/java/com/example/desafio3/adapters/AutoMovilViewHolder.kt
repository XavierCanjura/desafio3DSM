package com.example.desafio3.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio3.databinding.CardAutoBinding
import com.example.desafio3.databinding.CardBaseBinding

class AutoMovilViewHolder (view: View): RecyclerView.ViewHolder(view) {
    private val binding = CardAutoBinding.bind(view)

    fun render(data: ArrayList<String>, onClickListener: (ArrayList<String>) -> Unit){
        binding.tvMarca.text = data[0]
        binding.tvModelo.text = data[1]
        binding.tvPrecio.text = "$"+data[2]
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
            onClickListener(info)
        }
    }
}