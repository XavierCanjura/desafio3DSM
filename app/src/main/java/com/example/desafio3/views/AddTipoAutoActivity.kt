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

    private var idTipoAuto: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_tipo_auto)

        this.idTipoAuto = intent.getIntExtra("idtipoauto", 0)

        etDescripcionTipoAuto = findViewById(R.id.etDescripcionTipoAuto)
        btnAgregar = findViewById(R.id.btnAgregar)
        btnRegresar = findViewById(R.id.btnRegresar)

        if(idTipoAuto == 0) {
            btnAgregar.text = "Agregar"
        } else {
            btnAgregar.text = "Editar"
            TipoAutomovil = TipoAutomovil(this)
            var descripcionTipoAuto: String = TipoAutomovil!!.searchDescripcion(idTipoAuto).toString()
            etDescripcionTipoAuto.setText(descripcionTipoAuto)
        }

        btnAgregar.setOnClickListener {
            if(idTipoAuto == 0) {
                this.AgregarTipoAuto()
            } else {
                this.EditarTipoAuto()
            }
        }

        btnRegresar.setOnClickListener {
            val activity = Intent(this, MainActivity::class.java)
            startActivity(activity)
            finish()
        }
    }

    fun AgregarTipoAuto() {
        TipoAutomovil = TipoAutomovil(this)
        var DescripcionTipoAuto = etDescripcionTipoAuto.text.toString()

        TipoAutomovil!!.addTipoAuto(null, DescripcionTipoAuto)

        Toast.makeText(this, "Se creo correctamente el tipo de automovil", Toast.LENGTH_LONG).show()

        val activity = Intent(this, MainActivity::class.java)
        startActivity(activity)
        finish()
    }

    fun EditarTipoAuto(){
        TipoAutomovil = TipoAutomovil(this)
        var descripcionTipoAuto = etDescripcionTipoAuto.text.toString()

        TipoAutomovil!!.updateTipoAuto(idTipoAuto, descripcionTipoAuto)

        Toast.makeText(this, "Se modifico correctamente el tipo de auto", Toast.LENGTH_LONG).show()

        val activity = Intent(this, MainActivity::class.java)
        startActivity(activity)
        finish()
    }
}