package com.example.desafio3.models

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.desafio3.db.HelperDB

class Usuarios (context: Context?) {
    private var helper: HelperDB? = null
    private var db: SQLiteDatabase? = null

    init {
        helper = HelperDB(context)
        db = helper!!.writableDatabase
    }

    companion object {
        //TABLA USUARIOS
        val TABLE_NAME_USUARIOS = "usuarios"

        //nombre de los campos de la tabla usuarios
        val COL_ID_USUARIO = "idusuario"
        val COL_NOMBRES = "nombre"
        val COL_APELLIDOS = "apellidos"
        val COL_EMAIL = "email"
        val COL_USER = "user"
        val COL_PASSWORD = "password"
        val COL_TIPO = "tipo"

        //SENTENCIA SQL PARA CREAR LA TABLA
        val CREATE_TABLE_USUARIOS = (
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_USUARIOS + "("
                        + COL_ID_USUARIO +" integer primary key autoincrement, "
                        + COL_NOMBRES + " varchar(45) NOT NULL,"
                        + COL_APELLIDOS + " varchar(45) NOT NULL,"
                        + COL_EMAIL + " varchar(45) NOT NULL,"
                        + COL_USER + " varchar(45) unique NOT NULL,"
                        + COL_PASSWORD + " varchar(45) NOT NULL,"
                        + COL_TIPO + " varchar(45) NOT NULL);"
                )
    }

    // ContentValues
    fun generarContentValues(
        idusuario: Int?,
        nombres: String?,
        apellidos: String?,
        email: String?,
        user: String?,
        password: String?,
        tipo: String?
    ): ContentValues {
        val valores = ContentValues()
        valores.put(COL_ID_USUARIO, idusuario)
        valores.put(COL_NOMBRES, nombres)
        valores.put(COL_APELLIDOS, apellidos)
        valores.put(COL_EMAIL, email)
        valores.put(COL_USER, user)
        valores.put(COL_PASSWORD, password)
        valores.put(COL_TIPO, tipo)
        return valores
    }

    // Funcion para obtener a los usuarios
    fun getAllUsuarios(): Cursor? {
        val columns = arrayOf(COL_ID_USUARIO, COL_NOMBRES, COL_APELLIDOS, COL_EMAIL, COL_TIPO)
        return db!!.query(
            TABLE_NAME_USUARIOS,
            columns,
            null, null, null, null, "$COL_APELLIDOS ASC"
        )
    }

    // funcion para crear usuarios
    fun addUsuario(idusuario: Int?, nombres: String?, apellidos: String?, email: String?, user: String?, password: String?, tipo: String?) {
        db!!.insert(
            TABLE_NAME_USUARIOS,
            null,
            generarContentValues(idusuario, nombres, apellidos, email, user, password, tipo)
        )
    }

    //Funcion para modificar usuarios
    fun updateUsuario(idusuario: Int?, nombres: String?, apellidos: String?, email: String?, user: String?, password: String?, tipo: String?){
        db!!.update(
            TABLE_NAME_USUARIOS,
            generarContentValues(idusuario, nombres, apellidos, email, user, password, tipo),
            "$COL_ID_USUARIO=?", arrayOf(idusuario.toString())
        )
    }

    //Funcion para eliminar usuarios
    fun deleteUsuario(idusuario: Int?){
        db!!.delete(
            TABLE_NAME_USUARIOS,
            "$COL_ID_USUARIO=?",
            arrayOf(idusuario.toString())
        )
    }

    fun Login(usuario: String?, password: String?): Boolean{
        val colPass = arrayOf(COL_PASSWORD)
        var cursor: Cursor = db!!.query(
            TABLE_NAME_USUARIOS,
            colPass,
            "$COL_USER=?", arrayOf(usuario.toString()), null, null, null
        )

        cursor.moveToFirst()

        if(cursor.count == 0)
        {
            return false
        }

        if(cursor.getString(0).toString() != password){
            return false
        }

        return true
    }
}