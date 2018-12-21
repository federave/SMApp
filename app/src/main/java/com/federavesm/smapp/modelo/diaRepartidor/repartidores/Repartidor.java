package com.federavesm.smapp.modelo.diaRepartidor.repartidores;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.federavesm.smapp.modelo.Trabajador;

/**
 * Created by Federico on 2/1/2018.
 */

public class Repartidor extends Trabajador
{

    public Repartidor(Context context)
    {
        super(context);
    }

    @Override
    public boolean guardar()
    {
    try
        {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues repartidor = new ContentValues();
        repartidor.put("id",this.id);
        repartidor.put("nombre",this.nombre);
        repartidor.put("apellido",this.apellido);
        repartidor.put("dni",this.dni);
        long x = db.insert("Repartidores",null,repartidor);
        boolean aux = true;
        if(x==0){aux = false;}
        db.close();
        return aux;
        }
    catch (Exception e)
        {
        return false;
        }

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
    public boolean modificar() {
        return false;
    }

    public Repartidor(Context context,int id)
    {
    super(context);
    this.id=id;
    try {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Repartidores WHERE id=" + "'" + this.id + "'", null);
        if (cursor.moveToFirst())
            {
            this.nombre = cursor.getString(1);
            this.apellido = cursor.getString(2);
            this.dni = cursor.getString(3);
            }
        db.close();
        }
    catch (Exception e)
        {
        }
    }


    @Override
    public Object getCopia() {
        return null;
    }

    @Override
    public void copiar(Object object) {
    }

}