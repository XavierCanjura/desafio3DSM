package com.example.desafio3.models

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.desafio3.db.HelperDB

class Marcas(context: Context?) {
    private var helper: HelperDB? = null
    private var db: SQLiteDatabase? = null

    init {
        helper = HelperDB(context)
        db = helper!!.writableDatabase
    }

    companion object {
        //TABLA MARCAS
        val TABLE_NAME_MARCAS = "marcas"

        //nombre de los campos de la tabla marcas
        val COL_ID_MARCA = "idmarca"
        val COL_NOMBRE = "nombre"

        //SENTENCIA SQL PARA CREAR LA TABLA
        val CREATE_TABLE_MARCAS = (
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_MARCAS + "("
                        + COL_ID_MARCA +" integer primary key autoincrement, "
                        + COL_NOMBRE + " varchar(45) NOT NULL);"
                )
    }

    // ContentValues
    fun generarContentValues(
        idmarca: Int?,
        nombre: String?
    ) : ContentValues? {
        val valores = ContentValues()
        valores.put(COL_ID_MARCA, idmarca)
        valores.put(COL_NOMBRE, nombre)
        return  valores
    }

    // Funcion para obtener todas las marcas
    fun getAllMarcas(): Cursor? {
        val columns = arrayOf(COL_ID_MARCA, COL_NOMBRE)
        return db!!.query(
            TABLE_NAME_MARCAS,
            columns,
            null, null, null, null, "$COL_NOMBRE ASC"
        )
    }


    // Funcion para agregar marcas
    fun addMarca(idmarca: Int?, nombre: String?){
        db!!.insert(
            TABLE_NAME_MARCAS,
            null,
            generarContentValues(idmarca, nombre)
        )
    }

    // Funcion para modificar marcas
    fun updateMarca(idmarca: Int, nombre: String?){
        db!!.update(
            TABLE_NAME_MARCAS,
            generarContentValues(idmarca, nombre),
        "$COL_ID_MARCA=?", arrayOf(idmarca.toString())
        )
    }

    // Funcion para eliminar marcas
    fun deleteMarca(id: Int){
        db!!.delete(
            TABLE_NAME_MARCAS,
            "$COL_ID_MARCA=?",
            arrayOf(id.toString())
        )
    }

    fun searchID(nombre: String): Int? {
        val columns = arrayOf(COL_ID_MARCA, COL_NOMBRE)
        var cursor: Cursor? = db!!.query(
            TABLE_NAME_MARCAS,
            columns,
            "$COL_NOMBRE=?", arrayOf(nombre.toString()), null, null, null )
        cursor!!.moveToFirst()
        return cursor!!.getInt(0)
    }

    fun searchNombre(id: Int): String? {
        val columns = arrayOf(COL_ID_MARCA, COL_NOMBRE)
        var cursor: Cursor? = db!!.query(
            TABLE_NAME_MARCAS,
            columns,
            "$COL_ID_MARCA=?", arrayOf(id.toString()), null, null, null )
        cursor!!.moveToFirst()
        return cursor!!.getString(1)
    }
}