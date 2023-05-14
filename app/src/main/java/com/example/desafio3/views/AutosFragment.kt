package com.example.desafio3.views

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    private var list: ArrayList<Any>? = null

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

        list = ArrayList()
        var car = ArrayList<String>()

        var marca: Marcas = Marcas(context)
        if(cursor != null && cursor.count > 0){
            cursor.moveToFirst()
            do {
                car.clear()
                car.add(marca.searchNombre(cursor.getString(1).toInt()).toString())
                car.add(cursor.getString(2))
                car.add(cursor.getString(3))
                list!!.add(car)
            } while ( cursor.moveToNext() )
        }

        AutoMovilAdapter = AutoMovilAdapter(list!!)

        binding.rvCardList.adapter = AutoMovilAdapter
    }

}