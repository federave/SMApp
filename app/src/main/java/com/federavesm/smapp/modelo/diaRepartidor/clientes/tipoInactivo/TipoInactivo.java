package com.federavesm.smapp.modelo.diaRepartidor.clientes.tipoInactivo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.federavesm.smapp.R;
import com.federavesm.smapp.modelo.GenericoVista;

/**
 * Created by Federico on 13/2/2018.
 */


public class TipoInactivo extends GenericoVista {



    public TipoInactivo(Context context)
    {
        super(context);
    }

    public TipoInactivo(Context context,int id)
    {
    super(context);
    this.id=id;
    try
        {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM TipoInactivo WHERE id="+"'"+this.id+"'",null);
        if(cursor.moveToFirst())
            {
            this.tipoInactivo = cursor.getString(1);
            }
        db.close();
        }
    catch (Exception e)
        {
            String e2=e.toString();
        }
    }


    private String tipoInactivo;

    @Override
    public boolean modificar() {
        return false;
    }

    @Override
    public void copiar(Object object)
    {
    try
        {
        TipoInactivo tipoInactivo = (TipoInactivo)object;
        this.id = tipoInactivo.getId();
        this.tipoInactivo = tipoInactivo.getTipoInactivo();
        }
        catch (Exception e)
        {

        }
    }




    @Override
    public Object getCopia()
    {
    TipoInactivo tipoInactivo = new TipoInactivo(context);
    tipoInactivo.copiar(this);
    return tipoInactivo;
    }




















    public String getTipoInactivo() {
        return tipoInactivo;
    }

    public void setTipoInactivo(String tipoInactivo) {
        this.tipoInactivo = tipoInactivo;
    }

    @Override
    public boolean guardar()
    {
    try
        {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues tipoInactivo = new ContentValues();
        tipoInactivo.put("id",this.id);
        tipoInactivo.put("tipoInactivo",this.tipoInactivo);
        boolean aux = true;
        long x = db.insert("TipoInactivo",null,tipoInactivo);
        if(x==0){aux=false;}
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
    public boolean cargar()
    {
    try
        {
        boolean aux=false;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM TipoInactivo WHERE id="+"'"+this.id+"'",null);
        if(cursor.moveToFirst())
            {
            this.tipoInactivo = cursor.getString(1);
            aux=true;
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
    public int getRecursoImagen()
    {

    switch (this.id)
        {
        case 1:
            {
            return R.drawable.alquiler;
            }

        case 2:
            {
            return R.drawable.inactivoverde;
            }
        case 3:
            {
            return R.drawable.inactivoamarrillo;
            }
        case 4:
            {
            return R.drawable.inactivorojo;
            }
        case 5:
            {
            return R.drawable.clientenuevo;
            }
        default:
            {
            return -1;
            }


        }

    }


}
