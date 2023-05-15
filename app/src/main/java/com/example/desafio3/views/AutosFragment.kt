package com.example.desafio3.views

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desafio3.adapters.AutoMovilAdapter
import com.example.desafio3.adapters.MarcaAdapter
import com.example.desafio3.databinding.FragmentBaseBinding
import com.example.desafio3.models.Automoviles
import com.example.desafio3.models.Marcas

class AutosFragment : Fragment() {

    private var Automoviles: Automoviles? = null
    private var AutoMovilAdapter: AutoMovilAdapter? = null

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
            val activity = Intent(context, AddAutomovilActivity::class.java)
            startActivity(activity)
        }


        initRecyclerView()

        return view
    }

    private fun initRecyclerView(){
        binding.rvCardList.layoutManager = LinearLayoutManager(context)
        Automoviles = Automoviles(context)
        var cursor: Cursor? = Automoviles!!.getAllAutomoviles()

        list.clear()

        var marca: Marcas = Marcas(context)
        if(cursor != null && cursor.count > 0){
            cursor.moveToFirst()
            do {
                var car = ArrayList<String>()
                car.add(marca.searchNombre(cursor.getString(1).toInt()).toString())
                car.add(cursor.getString(2))
                car.add(cursor.getString(3))
                list!!.add(car)
            } while ( cursor.moveToNext() )
        }

        AutoMovilAdapter = AutoMovilAdapter(list!!){
            onItemClick(it[0], it[1])
        }

        binding.rvCardList.adapter = AutoMovilAdapter
    }

    private fun onItemClick(tipo: String, id: String) {
        if(tipo == "Editar"){
            editarAuto(id)
        } else {
            eliminarAuto(id)
        }
    }

    private fun editarAuto(id: String){
        val activity = Intent(context, AddMarcaActivity::class.java)
        activity.putExtra("idauto",id.toInt())
        startActivity(activity)
    }

    private fun eliminarAuto(id: String){
        Automoviles = Automoviles(context)
        Automoviles!!.deleteAutomovil(id.toInt())
        Toast.makeText(context, "Auto eliminado", Toast.LENGTH_LONG).show()
        initRecyclerView()
    }

}