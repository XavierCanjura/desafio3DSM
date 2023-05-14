package com.example.desafio3.views

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desafio3.adapters.ColorAdapter
import com.example.desafio3.databinding.FragmentBaseBinding
import com.example.desafio3.models.Colores

class ColoresFragment : Fragment() {

    private var Colores: Colores? = null
    private var colorAdapter: ColorAdapter? = null

    private var _binding: FragmentBaseBinding? = null
    private val binding get() = _binding!!

    private var list: ArrayList<Any>? = null

    private var clase: String = ""

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

        list = ArrayList()
        if(cursor != null && cursor.count > 0){
            cursor.moveToFirst()
            do {
                list!!.add(cursor.getString(1))
            } while ( cursor.moveToNext() )
        }

        colorAdapter = ColorAdapter(list!!)

        binding.rvCardList.adapter = colorAdapter

        Log.d("Data", list.toString())


    }

}