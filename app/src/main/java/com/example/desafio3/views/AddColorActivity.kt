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
import com.example.desafio3.models.Marcas

class AddColorActivity : AppCompatActivity() {
    private lateinit var tvDescripcionColor: EditText
    private lateinit var btnAgregar: Button
    private lateinit var btnRegresar: Button

    private var Colores: Colores? = null

    private var idColor: Int = 0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_color)

        this.idColor = intent.getIntExtra("idcolor", 0)

        tvDescripcionColor = findViewById(R.id.etDescripcionColor)
        btnAgregar = findViewById(R.id.btnAgregar)
        btnRegresar = findViewById(R.id.btnRegresar)

        if(idColor == 0){
            btnAgregar.text = "Agregar"
        } else {
            btnAgregar.text = "Editar"
            Colores = Colores(this)
            var descripcionColor: String = Colores!!.searchNombre(idColor).toString()
            tvDescripcionColor.setText(descripcionColor)
        }

        btnAgregar.setOnClickListener {
            if(idColor == 0){
                this.AddColor()
            } else {
                this.EditColor()
            }
        }

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

    fun EditColor(){
        Colores = Colores(this)
        var descripcionColor = tvDescripcionColor.text.toString()

        Colores!!.updateColor(idColor, descripcionColor)

        Toast.makeText(this, "Se modifco correctamente el color", Toast.LENGTH_LONG).show()

        val activity = Intent(this, MainActivity::class.java)
        startActivity(activity)
        finish()
    }
}