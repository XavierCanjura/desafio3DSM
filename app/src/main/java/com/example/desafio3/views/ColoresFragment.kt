package com.example.desafio3.views

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desafio3.adapters.ColorAdapter
import com.example.desafio3.databinding.FragmentBaseBinding
import com.example.desafio3.models.Colores
import com.example.desafio3.models.Marcas

class ColoresFragment : Fragment() {

    private var Colores: Colores? = null
    private var colorAdapter: ColorAdapter? = null

    private var _binding: FragmentBaseBinding? = null
    private val binding get() = _binding!!

    private var list: MutableList<Any> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBaseBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.fabAgregar.setOnClickListener {
            val activity = Intent(context, AddColorActivity::class.java)
            startActivity(activity)
        }


        initRecyclerView()

        return view
    }

    private fun initRecyclerView(){
        binding.rvCardList.layoutManager = LinearLayoutManager(context)
        Colores = Colores(context)
        var cursor: Cursor? = Colores!!.getAllColores()

        list.clear()
        if(cursor != null && cursor.count > 0){
            cursor.moveToFirst()
            do {
                val color = ArrayList<String>()
                color.add(cursor.getString(0))
                color.add(cursor.getString(1))
                list!!.add(color)
            } while ( cursor.moveToNext() )
        }

        colorAdapter = ColorAdapter(list!!) {
            onItemClick(it[0], it[1])
        }

        binding.rvCardList.adapter = colorAdapter
    }

    private fun onItemClick(tipo: String, id: String){
        if(tipo == "Editar"){
            editColor(id)
        } else {
            deleteColor(id)
        }
    }

    private fun editColor(id: String){
        val activity = Intent(context, AddColorActivity::class.java)
        activity.putExtra("idcolor",id.toInt())
        startActivity(activity)
    }

    private fun deleteColor(id: String){
        Colores = Colores(context)
        Colores!!.deleteColor(id.toInt())
        Toast.makeText(context, "Color Eliminado", Toast.LENGTH_LONG).show()
        initRecyclerView()
    }

}