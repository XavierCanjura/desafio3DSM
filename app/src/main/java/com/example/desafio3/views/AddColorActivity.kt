package com.example.desafio3.views

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.desafio3.R
import com.example.desafio3.models.Colores

class AddColorActivity : AppCompatActivity() {
    private lateinit var tvDescripcionColor: EditText
    private lateinit var btnAgregar: Button
    private lateinit var btnRegresar: Button

    private var Colores: Colores? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_color)

        tvDescripcionColor = findViewById(R.id.etDescripcionColor)
        btnAgregar = findViewById(R.id.btnAgregar)
        btnRegresar = findViewById(R.id.btnRegresar)

        btnAgregar.setOnClickListener { this.AddColor() }

        btnRegresar.setOnClickListener {
            val activity = Intent(this, MainActivity::class.java)
            startActivity(activity)
            finish()
        }
    }

    fun AddColor() {
        Colores = Colores(this)
        var descripcionColor = tvDescripcionColor.text.toString()

        Colores!!.addColor(null, descripcionColor)

        Toast.makeText(this, "Se creo correctamente el color", Toast.LENGTH_LONG).show()

        val activity = Intent(this, MainActivity::class.java)
        startActivity(activity)
        finish()
    }
}