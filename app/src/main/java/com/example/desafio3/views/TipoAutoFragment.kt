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
import com.example.desafio3.adapters.TipoAutoAdapter
import com.example.desafio3.databinding.FragmentBaseBinding
import com.example.desafio3.models.Marcas
import com.example.desafio3.models.TipoAutomovil

class TipoAutoFragment : Fragment() {

    private var TipoAutomovil: TipoAutomovil? = null
    private var tipoAutoAdapter: TipoAutoAdapter? = null

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
            val activity = Intent(context, AddTipoAutoActivity::class.java)
            startActivity(activity)
        }

        initRecyclerView()

        return view
    }

    private fun initRecyclerView(){
        binding.rvCardList.layoutManager = LinearLayoutManager(context)
        TipoAutomovil = TipoAutomovil(context)
        var cursor: Cursor? = TipoAutomovil!!.getAllTipoAuto()

        list.clear()
        if(cursor != null && cursor.count > 0){
            cursor.moveToFirst()
            do {
                val tipoAuto = ArrayList<String>()
                tipoAuto.add(cursor.getString(0))
                tipoAuto.add(cursor.getString(1))
                list!!.add(tipoAuto)
            } while ( cursor.moveToNext() )
        }

        tipoAutoAdapter = TipoAutoAdapter(list!!){
            onItemClick(it[0], it[1])
        }

        binding.rvCardList.adapter = tipoAutoAdapter
    }

    private fun onItemClick(tipo: String, id: String) {
        if(tipo == "Editar"){
            editarTipoAuto(id)
        } else {
            eliminarTipoAutoMarca(id)
        }
    }

    private fun editarTipoAuto(id: String){
        val activity = Intent(context, AddTipoAutoActivity::class.java)
        activity.putExtra("idtipoauto",id.toInt())
        startActivity(activity)
    }

    private fun eliminarTipoAutoMarca(id: String){
        TipoAutomovil = TipoAutomovil(context)
        TipoAutomovil!!.deleteTipoAuto(id.toInt())
        Toast.makeText(context, "Tipo de auto eliminado", Toast.LENGTH_LONG).show()
        initRecyclerView()
    }

}