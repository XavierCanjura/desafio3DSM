package com.example.desafio3.views

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.desafio3.R
import com.example.desafio3.models.Usuarios

class LoginActivity : AppCompatActivity() {

    private lateinit var etUsuario: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnRegistrarse: Button
    private lateinit var btnAcceder: Button

    private var Usuarios: Usuarios? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etUsuario = findViewById(R.id.etUsuario)
        etPassword = findViewById(R.id.etPassword)

        btnRegistrarse = findViewById(R.id.btnRegistrarme)
        btnAcceder = findViewById(R.id.btnAcceder)

        Usuarios = Usuarios(this)

        btnAcceder.setOnClickListener {
            var usuario = etUsuario.text.toString()
            var password = etPassword.text.toString()
            if(Usuarios!!.Login(usuario, password))
            {
                val credentials = getSharedPreferences("userInfo", Context.MODE_PRIVATE)
                var editor = credentials.edit()
                editor.putString("user", usuario)
                editor.commit()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Ingrese credenciales correctas", Toast.LENGTH_LONG).show()
            }


        }


        btnRegistrarse.setOnClickListener {
            val intent = Intent(this, AddUsuarioActivity::class.java)
            intent.putExtra("tipo", "signin")
            startActivity(intent)
        }
    }
}