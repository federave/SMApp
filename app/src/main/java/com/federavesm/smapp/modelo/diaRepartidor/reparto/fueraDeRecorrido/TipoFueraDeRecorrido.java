package com.federavesm.smapp.modelo.diaRepartidor.reparto.fueraDeRecorrido;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.federavesm.smapp.R;
import com.federavesm.smapp.modelo.GenericoVista;

/**
 * Created by Federico on 2/5/2018.
 */


public class TipoFueraDeRecorrido extends GenericoVista {



    public TipoFueraDeRecorrido(Context context)
    {
        super(context);
    }

    public TipoFueraDeRecorrido(Context context,int id)
    {
        super(context);
        this.id=id;
        try
        {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM TipoFueraDeRecorrido WHERE id="+"'"+this.id+"'",null);
            if(cursor.moveToFirst()){this.tipoFueraDeRecorrido = cursor.getString(1);}
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

    private String tipoFueraDeRecorrido;


    @Override
    public void copiar(Object object)
    {
        try
        {
            TipoFueraDeRecorrido tipoFueraDeRecorrido = (TipoFueraDeRecorrido)object;
            this.id = tipoFueraDeRecorrido.getId();
            this.tipoFueraDeRecorrido = tipoFueraDeRecorrido.getTipoFueraDeRecorrido();
        }
        catch (Exception e)
        {
        }
    }


    @Override
    public Object getCopia()
    {
        TipoFueraDeRecorrido tipoFueraDeRecorrido = new TipoFueraDeRecorrido(context);
        tipoFueraDeRecorrido.copiar(this);
        return tipoFueraDeRecorrido;
    }



















    public String getTipoFueraDeRecorrido() {
        return tipoFueraDeRecorrido;
    }

    public void setTipoFueraDeRecorrido(String tipoFueraDeRecorrido) {
        this.tipoFueraDeRecorrido = tipoFueraDeRecorrido;
    }

    @Override
    public boolean guardar()
    {
        try
        {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues tipoFueraDeRecorrido = new ContentValues();
            tipoFueraDeRecorrido.put("id",this.id);
            tipoFueraDeRecorrido.put("tipoFueraDeRecorrido",this.tipoFueraDeRecorrido);
            long x = db.insert("TipoFueraDeRecorrido",null,tipoFueraDeRecorrido);
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
    public boolean cargar()
    {
        try
        {
            boolean aux=false;
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM TipoFueraDeRecorrido WHERE id="+"'"+this.id+"'",null);
            if(cursor.moveToFirst()){this.tipoFueraDeRecorrido = cursor.getString(1);aux=true;}
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
                return R.drawable.llamado;
            }
            case 2:
            {
                return R.drawable.fueraderecorrido;
            }
            case 3:
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
