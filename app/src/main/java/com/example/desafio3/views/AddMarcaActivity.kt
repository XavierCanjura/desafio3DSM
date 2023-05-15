package com.example.desafio3.views

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.desafio3.R
import com.example.desafio3.models.Marcas

class AddMarcaActivity : AppCompatActivity() {

    private lateinit var tvNombreMarca: EditText
    private lateinit var btnAgregar: Button
    private lateinit var btnRegresar: Button

    private var Marcas: Marcas? = null

    private var idMarca: Int = 0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_marca)

        this.idMarca = intent.getIntExtra("idmarca", 0)

        tvNombreMarca = findViewById(R.id.etNombreMarca)
        btnAgregar = findViewById(R.id.btnAgregar)
        btnRegresar = findViewById(R.id.btnRegresar)

        if(idMarca != 0){
            btnAgregar.text = "Editar"
            Marcas = Marcas(this)
            var nombreMarca: String = Marcas!!.searchNombre(idMarca).toString()
            tvNombreMarca.setText(nombreMarca)
        } else {
            btnAgregar.text = "Agregar"
        }

        btnAgregar.setOnClickListener {
            if(idMarca == 0){
                this.AgregarMarca()
            } else {
                this.EditarMarca()
            }
        }

        btnRegresar.setOnClickListener {
            val activity = Intent(this, MainActivity::class.java)
            startActivity(activity)
            finish()
        }
    }

    fun AgregarMarca() {
        Marcas = Marcas(this)
        var nombreMarca = tvNombreMarca.text.toString()

        Marcas!!.addMarca(null, nombreMarca)

        Toast.makeText(this, "Se creo correctamente la marca", Toast.LENGTH_LONG).show()

        val activity = Intent(this, MainActivity::class.java)
        startActivity(activity)
        finish()
    }

    fun EditarMarca()
    {
        Marcas = Marcas(this)
        var nombreMarca = tvNombreMarca.text.toString()

        Marcas!!.updateMarca(idMarca, nombreMarca)

        Toast.makeText(this, "Se modifico correctamente la marca", Toast.LENGTH_LONG).show()

        val activity = Intent(this, MainActivity::class.java)
        startActivity(activity)
        finish()
    }
}