package com.example.desafio3.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.desafio3.models.*

class HelperDB(context: Context?) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        private const val DB_NAME = "carsmotors.sqlite"
        private const val DB_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(Marcas.CREATE_TABLE_MARCAS)
        db.execSQL(Colores.CREATE_TABLE_COLORES)
        db.execSQL(TipoAutomovil.CREATE_TABLE_TIPO_AUTOMOVIL)
        db.execSQL(Usuarios.CREATE_TABLE_USUARIOS)
        db.execSQL(Automoviles.CREATE_TABLE_AUTOMOVILES)
        db.execSQL(Favoritos.CREATE_TABLE_FAVORITOS)

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) { }
}