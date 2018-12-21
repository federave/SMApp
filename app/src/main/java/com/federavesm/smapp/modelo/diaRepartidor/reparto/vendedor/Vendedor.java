package com.federavesm.smapp.modelo.diaRepartidor.reparto.vendedor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.federavesm.smapp.modelo.Trabajador;

/**
 * Created by Federico on 9/2/2018.
 */

public class Vendedor extends Trabajador {



    public Vendedor(Context context)
    {
        super(context);
    }



    public Vendedor(Context context,int id)
    {

    super(context);
    this.id=id;
    try
        {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Vendedores WHERE id="+"'"+this.id+"'",null);
        if(cursor.moveToFirst())
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
    public boolean guardar()
    {
    try
        {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues vendedor = new ContentValues();
        vendedor.put("id",this.id);
        vendedor.put("nombre",this.nombre);
        vendedor.put("apellido",this.apellido);
        vendedor.put("dni",this.dni);
        long x = db.insert("Vendedores",null,vendedor);
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
    @Override
    public Object getCopia() {
        Vendedor vendedor = new Vendedor(context);
        vendedor.copiar(this);
        return vendedor;
    }

    @Override
    public void copiar(Object object) {super.copiar(object);
    }

}

