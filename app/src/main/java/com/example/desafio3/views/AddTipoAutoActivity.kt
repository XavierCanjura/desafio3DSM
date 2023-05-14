package com.example.desafio3.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.desafio3.R
import com.example.desafio3.models.Marcas
import com.example.desafio3.models.TipoAutomovil

class AddTipoAutoActivity : AppCompatActivity() {

    private lateinit var etDescripcionTipoAuto: EditText
    private lateinit var btnAgregar: Button
    private lateinit var btnRegresar: Button

    private var TipoAutomovil: TipoAutomovil? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_tipo_auto)

        etDescripcionTipoAuto = findViewById(R.id.etDescripcionTipoAuto)
        btnAgregar = findViewById(R.id.btnAgregar)
        btnRegresar = findViewById(R.id.btnRegresar)

        btnAgregar.setOnClickListener { this.AddMarca() }

        btnRegresar.setOnClickListener {
            val activity = Intent(this, MainActivity::class.java)
            startActivity(activity)
            finish()
        }
    }

    fun AddMarca() {
        TipoAutomovil = TipoAutomovil(this)
        var DescripcionTipoAuto = etDescripcionTipoAuto.text.toString()

        TipoAutomovil!!.addTipoAuto(null, DescripcionTipoAuto)

        Toast.makeText(this, "Se creo correctamente el tipo de automovil", Toast.LENGTH_LONG).show()

        val activity = Intent(this, MainActivity::class.java)
        startActivity(activity)
        finish()
    }
}