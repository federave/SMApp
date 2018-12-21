package com.federavesm.smapp.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Federico on 2/1/2018.
 */

public class Usuario extends Generico {


    public Usuario(Context context)
    {
        super(context);
    }




    public Usuario(Context context,int id)
    {
    super(context);
    this.id=id;
    try
        {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Usuarios WHERE id="+"'"+this.id+"'",null);
        if(cursor.moveToFirst())
            {
            this.nombre = cursor.getString(1);
            this.contraseña = cursor.getString(2);
            this.administrador = Convertidor.toBoolean(cursor.getInt(3));
            }
        db.close();
        }
    catch (Exception e)
        {

        }


    }






    @Override
    public boolean modificar() {
        return false;
    }

    private String nombre ="";
    private String contraseña ="";
    private boolean administrador;

    private boolean estado;
    public boolean getEstado() {
        return estado;
    }
    public void setEstado(boolean estado) {
        this.estado = estado;
    }


    public String getContraseña() {
        return contraseña;
    }
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean getAdministrador() {
        return administrador;
    }
    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }


    public boolean chequear()
    {

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Usuarios WHERE nombre="+"'"+this.nombre+"'"+"AND password="+"'"+this.contraseña+"'",null);

        if(cursor.moveToFirst())
        {
            this.administrador = (cursor.getInt(3)!=0);
            this.estado = true;
            return true;
        }
        else
        {
            return false;
        }

    }


    @Override
    public String toString() {
        return this.nombre ;
    }

    @Override
    public boolean guardar()
    {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues usuario = new ContentValues();
        usuario.put("nombre","admin");
        usuario.put("password","1351");
        usuario.put("administrador",1);

        long x = db.insert("Usuarios",null,usuario);

        if(x==0){return false;}
        else{return true;}

    }

    @Override
    public boolean eliminar() {
        return false;
    }

    @Override
    public boolean actualizar() {
        return false;
    }




    @Override
    public boolean cargar(){return true;}


    @Override
    public Object getCopia() {
        return null;
    }


    @Override
    public void copiar(Object object) {
    }




}
