package com.federavesm.smapp.modelo.diaRepartidor.clientes.tipoCliente;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;

import com.federavesm.smapp.R;
import com.federavesm.smapp.modelo.Generico;
import com.federavesm.smapp.modelo.GenericoVista;
import com.federavesm.smapp.modelo.diaRepartidor.clientes.Datos;

/**
 * Created by Federico on 8/2/2018.
 */

public class TipoCliente extends GenericoVista {



    public TipoCliente(Context context)
    {
        super(context);
    }

    public TipoCliente(Context context,int id)
    {
    super(context);
    this.id=id;
    try
        {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM TipoCliente WHERE id="+"'"+this.id+"'",null);
        if(cursor.moveToFirst()){this.tipoCliente = cursor.getString(1);}
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

    private String tipoCliente;


    @Override
    public void copiar(Object object)
    {
    try
        {
        TipoCliente tipoCliente = (TipoCliente)object;
        this.id = tipoCliente.getId();
        this.tipoCliente = tipoCliente.getTipoCliente();
        }
    catch (Exception e)
        {
        }
    }


    @Override
    public Object getCopia()
    {
    TipoCliente tipoCliente = new TipoCliente(context);
    tipoCliente.copiar(this);
    return tipoCliente;
    }





    public String getTipoCliente()
    {
    if(this.id>0)
        return tipoCliente;
    else
        return "no hay informacion";
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    @Override
    public boolean guardar()
    {
    try
        {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues tipoCliente = new ContentValues();
        tipoCliente.put("id",this.id);
        tipoCliente.put("tipoCliente",this.tipoCliente);
        long x = db.insert("TipoCliente",null,tipoCliente);
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
        if(this.id>0)
            {
                try
                {
                    boolean aux=false;
                    SQLiteDatabase db = getReadableDatabase();
                    Cursor cursor = db.rawQuery("SELECT * FROM TipoCliente WHERE id="+"'"+this.id+"'",null);
                    if(cursor.moveToFirst()){this.tipoCliente = cursor.getString(1);aux=true;}
                    db.close();
                    return aux;
                }
                catch (Exception e)
                {
                    return false;
                }
            }
        else
            {
            return true;
            }
    }



    @Override
    public int getRecursoImagen()
    {

    switch (this.id)
        {
        case 1:
            {
            return R.drawable.domicilio;
            }
        case 2:
            {
            return R.drawable.comercio;
            }
        case 3:
            {
            return R.drawable.comercio;
            }
        case 4:
            {
            return R.drawable.comercio;
            }
        case 5:
            {
            return R.drawable.supermercado;
            }
        case 6:
            {
            return R.drawable.supermercado;
            }
        case 7:
            {
            return R.drawable.centrodeportivo;
            }
        case 8:
            {
            return R.drawable.institucion;
            }
        case 9:
            {
            return R.drawable.hospital;
            }
        case 10:
            {
            return R.drawable.institucion;
            }
        case 11:
            {
            return R.drawable.institucion;
            }
        case 12:
            {
            return -1;
            }
        case 13:
            {
            return R.drawable.universidad;
            }
        default:
            {
            return R.drawable.incognita;
            }
        }
    }



}
