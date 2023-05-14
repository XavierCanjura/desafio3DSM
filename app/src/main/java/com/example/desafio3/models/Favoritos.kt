package com.example.desafio3.models

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.desafio3.db.HelperDB

class Favoritos (context: Context?) {
    private var helper: HelperDB? = null
    private var db: SQLiteDatabase? = null

    init {
        helper = HelperDB(context)
        db = helper!!.writableDatabase
    }

    companion object {
        //TABLA FAVORITOS
        val TABLE_NAME_FAVORITOS = "favoritos"

        //nombre de los campos de la tabla favoritos
        val COL_ID_FAVORITO = "idfavorito"
        val COL_ID_USUARIO = "idusuario"
        val COL_ID_AUTOMOVIL = "idautomovil"
        val COL_FECHA_AGREGADO = "fecha_agregado"

        //SENTENCIA SQL PARA CREAR LA TABLA
        val CREATE_TABLE_FAVORITOS = (
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_FAVORITOS + "("
                        + COL_ID_FAVORITO +" integer primary key autoincrement, "
                        + COL_ID_USUARIO + " integer, "
                        + COL_ID_AUTOMOVIL + " integer, "
                        + COL_FECHA_AGREGADO + " DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,"
                        + "FOREIGN KEY (idusuario) REFERENCES usuarios(idusuario),"
                        + "FOREIGN KEY (idautomovil) REFERENCES automoviles(idautomovil) );"
                )
    }
}