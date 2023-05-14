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
import com.example.desafio3.adapters.MarcaAdapter
import com.example.desafio3.databinding.FragmentBaseBinding
import com.example.desafio3.models.Marcas


class MarcaFragment : Fragment() {
    private var Marcas: Marcas? = null
    private var marcaAdapter: MarcaAdapter? = null

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
            val activity = Intent(context, AddMarcaActivity::class.java)
            startActivity(activity)
        }


        initRecyclerView()

        return view
    }

    private fun initRecyclerView(){
        binding.rvCardList.layoutManager = LinearLayoutManager(context)
        Marcas = Marcas(context)
        var cursor: Cursor? = Marcas!!.getAllMarcas()

        list.clear()

        if(cursor != null && cursor.count > 0){
            cursor.moveToFirst()
            do {
                var marcas = ArrayList<String>()
                marcas.add(cursor.getString(0))
                marcas.add(cursor.getString(1))
                list.add(marcas)
                Log.d("InfoList", list.toString())
            } while ( cursor.moveToNext() )
        }


        //Log.d("InfoDB", list.toString())

        marcaAdapter = MarcaAdapter(list){
            onItemClick(it[0], it[1])
        }

        binding.rvCardList.adapter = marcaAdapter
    }

    private fun onItemClick(tipo: String, id: String) {
        Log.d("Data", "$tipo - $id")
        if(tipo == "Editar"){
            editMarca(id)
        } else {
            deleteMarca(id)
        }
    }

    private fun editMarca(id: String){
        val activity = Intent(context, AddMarcaActivity::class.java)
        activity.putExtra("idmarca",id.toInt())
        startActivity(activity)
    }

    private fun deleteMarca(id: String){
        Marcas = Marcas(context)
        Marcas!!.deleteMarca(id.toInt())
        Toast.makeText(context, "Marca Eliminada", Toast.LENGTH_LONG).show()
    }
}