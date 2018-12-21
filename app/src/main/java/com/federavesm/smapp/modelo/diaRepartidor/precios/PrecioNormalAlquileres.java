package com.federavesm.smapp.modelo.diaRepartidor.precios;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Federico on 8/2/2018.
 */


public class PrecioNormalAlquileres extends PrecioAlquileres {


    public PrecioNormalAlquileres(Context context)
    {
        super(context);
    }



    @Override
    public boolean getEspecial() {
        return false;
    }



    @Override
    public boolean guardar()
    {
    this.id = -1;// para que cuando se reciben clientes luego de cargar la planilla, no se superpongan los precios especiales con los normales
    return true;
    }

    @Override
    public boolean eliminar() {
        return false;
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
    public boolean getEstado() {
        return false;
    }


    @Override
    public boolean modificar() {
        return false;
    }

    @Override
    public boolean cargar()
    {

    try
        {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM PrecioDiaRepartidor WHERE idDiaRepartidor="+"'"+this.idDiaRepartidor+"'",null);
        boolean aux=false;
        if(cursor.moveToFirst())
            {
            aux=true;
            this.id = cursor.getInt(0);
            this.alquiler6Bidones = cursor.getFloat(9);
            this.alquiler8Bidones = cursor.getFloat(10);
            this.alquiler10Bidones = cursor.getFloat(11);
            this.alquiler12Bidones = cursor.getFloat(12);
            }
        db.close();
        return aux;
        }
    catch (Exception e)
        {
        return  false;
        }
    }

    @Override
    public Object getCopia()
    {
    PrecioNormalAlquileres precioNormalAlquileres = new PrecioNormalAlquileres(context);
    precioNormalAlquileres.copiar(this);
    return precioNormalAlquileres;
    }

    @Override
    public void copiar(Object object)
    {
    super.copiar(object);
    }
}
