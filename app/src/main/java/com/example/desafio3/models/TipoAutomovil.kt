package com.example.desafio3.models

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.desafio3.db.HelperDB

class TipoAutomovil (context: Context?) {
    private var helper: HelperDB? = null
    private var db: SQLiteDatabase? = null

    init {
        helper = HelperDB(context)
        db = helper!!.writableDatabase
    }

    companion object {
        //TABLA TIPOAUTOMOVIL
        val TABLE_NAME_TIPO_AUTOMOVIL = "tipo_automovil"

        //nombre de los campos de la tabla tipo automovil
        val COL_ID_TIPO_AUTOMOVIL = "idtipoautomovil"
        val COL_DESCRIPCION = "descripcion"

        //SENTENCIA SQL PARA CREAR LA TABLA
        val CREATE_TABLE_TIPO_AUTOMOVIL = (
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_TIPO_AUTOMOVIL + "("
                        + COL_ID_TIPO_AUTOMOVIL +" integer primary key autoincrement, "
                        + COL_DESCRIPCION + " varchar(45) NOT NULL);"
                )
    }

    // ContentValues
    fun generarContentValues(
        idtipoauto: Int?,
        descripcion: String?
    ) : ContentValues? {
        val valores = ContentValues()
        valores.put(COL_ID_TIPO_AUTOMOVIL, idtipoauto)
        valores.put(COL_DESCRIPCION, descripcion)
        return  valores
    }

    //Funcion para obtener todos los tipos de automovil
    fun getAllTipoAuto(): Cursor ? {
        val columns = arrayOf(COL_ID_TIPO_AUTOMOVIL, COL_DESCRIPCION)
        return db!!.query(
            TABLE_NAME_TIPO_AUTOMOVIL,
            columns,
            null, null, null, null, "$COL_ID_TIPO_AUTOMOVIL ASC"
        )
    }

    //Funcion para agregar tipoautomoviles
    fun addTipoAuto(idtipoauto: Int?, descripcion: String?){
        db!!.insert(
            TABLE_NAME_TIPO_AUTOMOVIL,
            null,
            generarContentValues(idtipoauto, descripcion)
        )
    }

    //Funcion para actualizar tipoautomoviles
    fun updateTipoAuto(idtipoauto: Int?, descripcion: String?){
        db!!.update(
            TABLE_NAME_TIPO_AUTOMOVIL,
            generarContentValues(idtipoauto, descripcion),
            "$COL_ID_TIPO_AUTOMOVIL=?", arrayOf(idtipoauto.toString())
        )
    }

    // Funcion para eliminar tipo de automovil
    fun deleteTipoAuto(idtipoauto: Int?){
        db!!.delete(
            TABLE_NAME_TIPO_AUTOMOVIL,
            "$COL_ID_TIPO_AUTOMOVIL=?",
            arrayOf(idtipoauto.toString())
        )
    }

    fun searchID(nombre: String): Int? {
        val columns = arrayOf(COL_ID_TIPO_AUTOMOVIL, COL_DESCRIPCION)
        var cursor: Cursor? = db!!.query(
            TABLE_NAME_TIPO_AUTOMOVIL,
            columns,
            "${COL_DESCRIPCION}=?", arrayOf(nombre.toString()), null, null, null )
        cursor!!.moveToFirst()
        return cursor!!.getInt(0)
    }

    fun searchDescripcion(id: Int): String? {
        val columns = arrayOf(COL_ID_TIPO_AUTOMOVIL, COL_DESCRIPCION)
        var cursor: Cursor? = db!!.query(
            TABLE_NAME_TIPO_AUTOMOVIL,
            columns,
            "${COL_ID_TIPO_AUTOMOVIL}=?", arrayOf(id.toString()), null, null, null )
        cursor!!.moveToFirst()
        return cursor!!.getString(1)
    }
}