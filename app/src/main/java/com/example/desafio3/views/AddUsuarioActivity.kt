package com.example.desafio3.views

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.example.desafio3.R
import com.example.desafio3.models.Marcas
import com.example.desafio3.models.Usuarios

class AddUsuarioActivity : AppCompatActivity() {

    private lateinit var etNombres: EditText
    private lateinit var etApellidos: EditText
    private lateinit var etEmail: EditText
    private lateinit var etUsuario: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConfirmPassword: EditText
    private lateinit var spTipo: Spinner
    private lateinit var btnAgregar: Button
    private lateinit var btnRegresar: Button

    private var Usuarios: Usuarios? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_usuario)

        etNombres = findViewById(R.id.etNombres)
        etApellidos = findViewById(R.id.etApellidos)
        etEmail = findViewById(R.id.etEmail)
        etUsuario = findViewById(R.id.etUsuario)
        etPassword = findViewById(R.id.etPassword)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)
        spTipo = findViewById(R.id.spTipoUsuario)


        btnAgregar = findViewById(R.id.btnAgregar)
        btnRegresar = findViewById(R.id.btnRegresar)

        setSpinnerTipoUsuario()

        btnAgregar.setOnClickListener { this.AddUsuario() }

        btnRegresar.setOnClickListener {
            val activity = Intent(this, MainActivity::class.java)
            startActivity(activity)
            finish()
        }
    }

    fun AddUsuario() {
        Usuarios = Usuarios(this)

        var nombres = etNombres.text.toString()
        var apellidos = etApellidos.text.toString()
        var email = etEmail.text.toString()
        var usuario = etUsuario.text.toString()
        var password = etPassword.text.toString()
        var confirmPassword = etConfirmPassword.text.toString()
        var tipoUser = spTipo.selectedItem.toString()

        Usuarios!!.addUsuario(null, nombres, apellidos, email, usuario, password, tipoUser)

        Toast.makeText(this, "Se creo correctamente el usuario", Toast.LENGTH_LONG).show()

        val activity = Intent(this, MainActivity::class.java)
        startActivity(activity)
        finish()
    }

    fun setSpinnerTipoUsuario(){
        var userType = ArrayList<String>()
        userType.add("ADMIN")
        userType.add("CLIENTE")

        var adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, userType)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spTipo.adapter = adapter
    }
}