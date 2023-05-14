package com.example.desafio3.models

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.desafio3.db.HelperDB

class Colores (context: Context?) {
    private var helper: HelperDB? = null
    private var db: SQLiteDatabase? = null

    init {
        helper = HelperDB(context)
        db = helper!!.writableDatabase
    }

    companion object {
        //TABLA COLORES
        val TABLE_NAME_COLORES = "colores"

        //nombre de los campos de la tabla colores
        val COL_ID_COLORE = "idcolore"
        val COL_DESCRIPCION = "descripcion"

        //SENTENCIA SQL PARA CREAR LA TABLA
        val CREATE_TABLE_COLORES = (
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_COLORES + "("
                        + COL_ID_COLORE +" integer primary key autoincrement, "
                        + COL_DESCRIPCION + " varchar(45) NOT NULL);"
                )
    }

    // ContentValues
    fun generarContentValues(
        idcolor: Int?,
        descripcion: String?
    ) : ContentValues? {
        val valores = ContentValues()
        valores.put(COL_ID_COLORE, idcolor)
        valores.put(COL_DESCRIPCION, descripcion)
        return  valores
    }

    // Funcion para obtener todas las marcas
    fun getAllColores(): Cursor? {
        val columns = arrayOf(COL_ID_COLORE, COL_DESCRIPCION)
        return db!!.query(
            TABLE_NAME_COLORES,
            columns,
            null, null, null, null, "${COL_DESCRIPCION} ASC"
        )
    }

    //Funcion para agregar colores
    fun addColor(idcolor: Int?, descripcion: String){
        db!!.insert(
            TABLE_NAME_COLORES,
            null,
            generarContentValues(idcolor, descripcion)
        )
    }

    //Funcion para modificar colores
    fun updateColor(idcolor: Int?, descripcion: String){
        db!!.update(
            TABLE_NAME_COLORES,
            generarContentValues(idcolor, descripcion),
            "$COL_ID_COLORE=?", arrayOf(idcolor.toString())
        )
    }

    //Funcion para eliminar colores
    fun deleteColor(idcolor: Int?){
        db!!.delete(
            TABLE_NAME_COLORES,
            "$COL_ID_COLORE=?",
            arrayOf(idcolor.toString())
        )
    }

    fun searchID(nombre: String): Int? {
        val columns = arrayOf(COL_ID_COLORE, COL_DESCRIPCION)
        var cursor: Cursor? = db!!.query(
            TABLE_NAME_COLORES,
            columns,
            "${COL_DESCRIPCION}=?", arrayOf(nombre.toString()), null, null, null )
        cursor!!.moveToFirst()
        return cursor!!.getInt(0)
    }

    fun searchNombre(id: Int): String? {
        val columns = arrayOf(COL_ID_COLORE, COL_DESCRIPCION)
        var cursor: Cursor? = db!!.query(
            TABLE_NAME_COLORES,
            columns,
            "${COL_ID_COLORE}=?", arrayOf(id.toString()), null, null, null )
        cursor!!.moveToFirst()
        return cursor!!.getString(1)
    }

}