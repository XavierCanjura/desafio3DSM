package com.example.desafio3.views

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.example.desafio3.R
import com.example.desafio3.models.Automoviles
import com.example.desafio3.models.Colores
import com.example.desafio3.models.Marcas
import com.example.desafio3.models.TipoAutomovil

class AddAutomovilActivity : AppCompatActivity() {


    private lateinit var spMarcas: Spinner
    private lateinit var etModelo: EditText
    private lateinit var spTipoAuto: Spinner
    private lateinit var spColores: Spinner
    private lateinit var etNumeroVin: EditText
    private lateinit var etNumeroChasis: EditText
    private lateinit var etNumeroMotor: EditText
    private lateinit var etNumeroAsientos: EditText
    private lateinit var etAnio: EditText
    private lateinit var etCapacidadAsientos: EditText
    private lateinit var etPrecio: EditText
    private lateinit var etDescripcion: EditText

    private lateinit var btnAgregar: Button
    private lateinit var btnRegresar: Button

    private var Automoviles: Automoviles? = null
    private var Marcas: Marcas? = null
    private var TipoAutomovil: TipoAutomovil? = null
    private var Colores: Colores? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_automovil)

        spMarcas = findViewById(R.id.spMarca)
        etModelo = findViewById(R.id.etModelo)
        spTipoAuto = findViewById(R.id.spTipoAuto)
        spColores = findViewById(R.id.spColor)
        etNumeroVin= findViewById(R.id.etVin)
        etNumeroChasis = findViewById(R.id.etChasis)
        etNumeroMotor = findViewById(R.id.etNumeroMotor)
        etNumeroAsientos = findViewById(R.id.etNumeroAsientos)
        etAnio = findViewById(R.id.etAnio)
        etCapacidadAsientos = findViewById(R.id.etCapacidadAsientos)
        etPrecio = findViewById(R.id.etPrecio)
        etDescripcion = findViewById(R.id.etDescripcion)

        btnAgregar = findViewById(R.id.btnAgregar)
        btnRegresar = findViewById(R.id.btnRegresar)

        setSpinnerMarcas()
        setSpinnerTipoAuto()
        setSpinnerColores()

        btnAgregar.setOnClickListener { this.AddAutoMovil() }

        btnRegresar.setOnClickListener {
            val activity = Intent(this, MainActivity::class.java)
            startActivity(activity)
            finish()
        }
    }

    fun AddAutoMovil() {
        Automoviles = Automoviles(this)

        var marca = spMarcas.selectedItem.toString()
        var idmarca = Marcas!!.searchID(marca)
        var modelo = etModelo.text.toString().trim()
        var tipoAuto = spTipoAuto.selectedItem.toString()
        var idtipoauto = TipoAutomovil!!.searchID(tipoAuto)
        var color = spColores.selectedItem.toString()
        var idcolor = Colores!!.searchID(color)
        var numerovin = etNumeroVin.text.toString().trim()
        var numerochasis = etNumeroChasis.text.toString().trim()
        var numeromotor = etNumeroMotor.text.toString().trim()
        var numeroasientos = etNumeroAsientos.text.toString().trim()
        var anio = etAnio.text.toString().trim()
        var capacidadasientos = etCapacidadAsientos.text.toString().trim()
        var precio = etPrecio.text.toString().trim()
        var descripcion = etDescripcion.text.toString().trim()

        Automoviles!!.addAutomovil(null, modelo, numerovin, numerochasis, numeromotor, numeroasientos.toInt(), anio.toInt(), capacidadasientos.toInt(), precio.toDouble(), "", descripcion, idmarca, idtipoauto, idcolor)

        Toast.makeText(this, "Se creo correctamente el automovil", Toast.LENGTH_LONG).show()

        val activity = Intent(this, MainActivity::class.java)
        startActivity(activity)
        finish()
    }

    fun setSpinnerMarcas()
    {
        Marcas = Marcas(this)
        var cursor: Cursor? = Marcas!!.getAllMarcas()

        var marcas = ArrayList<String>()
        if(cursor != null && cursor.count > 0) {
            cursor.moveToFirst()
            do {
                marcas.add(cursor.getString(1))
            } while (cursor.moveToNext())
        }

        var adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, marcas)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spMarcas.adapter = adapter
    }

    fun setSpinnerTipoAuto()
    {
        TipoAutomovil = TipoAutomovil(this)
        var cursor: Cursor? = TipoAutomovil!!.getAllTipoAuto()

        var tiposAuto = ArrayList<String>()
        if(cursor != null && cursor.count > 0) {
            cursor.moveToFirst()
            do {
                tiposAuto.add(cursor.getString(1))
            } while (cursor.moveToNext())
        }

        var adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, tiposAuto)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spTipoAuto.adapter = adapter
    }

    fun setSpinnerColores()
    {
        Colores = Colores(this)
        var cursor: Cursor? = Colores!!.getAllColores()

        var colores = ArrayList<String>()
        if(cursor != null && cursor.count > 0) {
            cursor.moveToFirst()
            do {
                colores.add(cursor.getString(1))
            } while (cursor.moveToNext())
        }

        var adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, colores)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spColores.adapter = adapter
    }
}