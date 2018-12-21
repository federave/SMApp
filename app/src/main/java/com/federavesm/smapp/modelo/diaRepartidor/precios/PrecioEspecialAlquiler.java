package com.federavesm.smapp.modelo.diaRepartidor.precios;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Federico on 8/2/2018.
 */

public class PrecioEspecialAlquiler extends PrecioAlquileres {


    public PrecioEspecialAlquiler(Context context)
    {
    super(context);
    }




    @Override
    public boolean getEspecial() {
        return true;
    }







    @Override
    public boolean guardar()
    {
    try
        {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues precioEspecialAlquileres = new ContentValues();
        precioEspecialAlquileres.put("alquiler6Bidones",this.alquiler6Bidones);
        precioEspecialAlquileres.put("alquiler8Bidones",this.alquiler8Bidones);
        precioEspecialAlquileres.put("alquiler10Bidones",this.alquiler10Bidones);
        precioEspecialAlquileres.put("alquiler126Bidones",this.alquiler12Bidones);

        boolean aux = true;
        if(db.insert("PrecioEspecialAlquileres",null,precioEspecialAlquileres) > 0)
            {
            this.id = getLastId("PrecioEspecialAlquileres");
            }
        else
            {
            aux=false;
            }
        db.close();
        return aux;
        }
    catch (Exception e)
        {
        return false;
        }
    }


    @Override
    public boolean cargar()
    {
    try
        {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM PrecioEspecialAlquiler WHERE id="+"'"+this.id+"'",null);
        boolean aux = false;
        if(cursor.moveToFirst())
            {
            aux = true;
            this.alquiler6Bidones = cursor.getFloat(1);
            this.alquiler8Bidones = cursor.getFloat(2);
            this.alquiler10Bidones = cursor.getFloat(3);
            this.alquiler12Bidones = cursor.getFloat(4);
            }
        db.close();
        return aux;
        }
    catch (Exception e)
        {
        return false;
        }
    }



    @Override
    public boolean eliminar()
    {
    try
        {
        SQLiteDatabase db = getWritableDatabase();
        boolean aux = true;
        if(!(db.delete("PrecioEspecialAlquiler", "id=" + "'" + this.id + "'", null)>0))
            {aux=false;}
        db.close();
        return aux;
        }
    catch (Exception e)
        {
        return false;
        }
    }


    @Override
    public boolean evaluar() {
        return false;
    }

    @Override
    public boolean actualizar() {
        return false;
    }

    @Override
    public String getEvaluar() {
        return null;
    }

    @Override
    public String getXMLToSend() {
        return null;
    }

    @Override
    public boolean modificar() {
        return false;
    }

    @Override
    public boolean getEstado() {
        return false;
    }


    @Override
    public Object getCopia()
    {
        PrecioEspecialAlquiler precioEspecialAlquiler = new PrecioEspecialAlquiler(context);
        precioEspecialAlquiler.copiar(this);
        return precioEspecialAlquiler;
    }

    @Override
    public void copiar(Object object)
    {
        super.copiar(object);
    }
}
