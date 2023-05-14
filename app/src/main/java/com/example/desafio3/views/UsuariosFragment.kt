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
import com.example.desafio3.adapters.MarcaAdapter
import com.example.desafio3.adapters.UsuarioAdapter
import com.example.desafio3.databinding.FragmentBaseBinding
import com.example.desafio3.models.Marcas
import com.example.desafio3.models.Usuarios

class UsuariosFragment : Fragment() {

    private var Usuarios: Usuarios? = null
    private var usuarioAdapter: UsuarioAdapter? = null

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
            val activity = Intent(context, AddUsuarioActivity::class.java)
            startActivity(activity)
        }


        initRecyclerView()

        return view
    }

    private fun initRecyclerView(){
        binding.rvCardList.layoutManager = LinearLayoutManager(context)
        Usuarios = Usuarios(context)
        var cursor: Cursor? = Usuarios!!.getAllUsuarios()

        list = ArrayList()
        var user = ArrayList<String>()
        if(cursor != null && cursor.count > 0){
            cursor.moveToFirst()
            do {
                user.clear()
                user.add(cursor.getString(0))
                user.add(cursor.getString(1))
                user.add(cursor.getString(2))
                user.add(cursor.getString(3))
                user.add(cursor.getString(4))
                list!!.add(user)
            } while ( cursor.moveToNext() )
        }

        usuarioAdapter = UsuarioAdapter(list!!)

        binding.rvCardList.adapter = usuarioAdapter
    }

}