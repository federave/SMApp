package com.federavesm.smapp.modelo.diaRepartidor.precios;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Federico on 8/2/2018.
 */


public class PrecioEspecialProductos extends PrecioProductos {


    public PrecioEspecialProductos(Context context)
    {
        super(context);
    }


    @Override
    public boolean getEspecial() {
        return true;
    }


    @Override
    public boolean cargar()
    {
    try
        {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM PrecioEspecialProductos WHERE id="+"'"+this.id+"'",null);
        boolean aux=false;
        if(cursor.moveToFirst())
            {
            aux=true;
            this.precioRetornables.setBidon20L(cursor.getFloat(1));
            this.precioRetornables.setBidon12L(cursor.getFloat(2));
            this.precioDescartables.setBidon10L(cursor.getFloat(3));
            this.precioDescartables.setBidon8L(cursor.getFloat(4));
            this.precioDescartables.setBidon5L(cursor.getFloat(5));
            this.precioDescartables.setPackBotellas2L(cursor.getFloat(6));
            this.precioDescartables.setPackBotellas500mL(cursor.getFloat(7));
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
    public boolean guardar()
    {
    try
        {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues precioEspecialProductos = new ContentValues();
        precioEspecialProductos.put("bidon20L",this.precioRetornables.getBidon20L());
        precioEspecialProductos.put("bidon12L",this.precioRetornables.getBidon12L());
        precioEspecialProductos.put("bidon10L",this.precioDescartables.getBidon10L());
        precioEspecialProductos.put("bidon8L",this.precioDescartables.getBidon8L());
        precioEspecialProductos.put("bidon5L",this.precioDescartables.getBidon5L());
        precioEspecialProductos.put("packBotellas2L",this.precioDescartables.getPackBotellas2L());
        precioEspecialProductos.put("packBotellas500mL",this.precioDescartables.getPackBotellas500mL());
        boolean aux = true;
        if(db.insert("PrecioEspecialProductos",null,precioEspecialProductos) > 0)
            {
            this.id = getLastId("PrecioEspecialProductos");
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
    public boolean getEstado() {
        return false;
    }




    @Override
    public boolean eliminar()
    {
    try
        {
        SQLiteDatabase db = getWritableDatabase();
        boolean aux = false;
        if(db.delete("PrecioEspecialProductos", "id=" + "'" + this.id + "'", null)>0)
            {aux=true;}
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
    public Object getCopia()
    {
        PrecioEspecialProductos precioEspecialProductos = new PrecioEspecialProductos(context);
        precioEspecialProductos.copiar(this);
        return precioEspecialProductos;
    }

    @Override
    public void copiar(Object object)
    {
        super.copiar(object);
    }









}
