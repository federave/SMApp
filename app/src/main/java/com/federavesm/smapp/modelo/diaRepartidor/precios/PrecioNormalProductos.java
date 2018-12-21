package com.federavesm.smapp.modelo.diaRepartidor.precios;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Federico on 8/2/2018.
 */



public class PrecioNormalProductos extends PrecioProductos {


    public PrecioNormalProductos(Context context)
    {
        super(context);
    }



    @Override
    public boolean getEspecial() {
        return false;
    }


    @Override
    public boolean eliminar() {
        return true;
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
    public boolean guardar()
    {
    this.id = -1; // para que cuando se reciben clientes luego de cargar la planilla, no se superpongan los precios especiales con los normales
    return true;
    }


    @Override
    public boolean getEstado() {
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
            aux = true;
            this.id = cursor.getInt(0);
            this.precioRetornables.setBidon20L(cursor.getFloat(2));
            this.precioRetornables.setBidon12L(cursor.getFloat(3));
            this.precioDescartables.setBidon10L(cursor.getFloat(4));
            this.precioDescartables.setBidon8L(cursor.getFloat(5));
            this.precioDescartables.setBidon5L(cursor.getFloat(6));
            this.precioDescartables.setPackBotellas2L(cursor.getFloat(7));
            this.precioDescartables.setPackBotellas500mL(cursor.getFloat(8));
            }
        db.close();
        return aux;
        }
    catch (Exception e)
        {
        String x=e.toString();
        return false;
        }
    }

    @Override
    public boolean modificar() {
        return false;
    }

    @Override
    public Object getCopia()
    {
        PrecioNormalProductos precioNormalProductos = new PrecioNormalProductos(context);
        precioNormalProductos.copiar(this);
        return precioNormalProductos;
    }

    @Override
    public void copiar(Object object)
    {
        super.copiar(object);
    }



}
