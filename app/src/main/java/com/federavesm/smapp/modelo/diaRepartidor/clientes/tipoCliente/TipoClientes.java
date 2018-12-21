package com.federavesm.smapp.modelo.diaRepartidor.clientes.tipoCliente;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.federavesm.smapp.modelo.Generico;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Federico on 13/2/2018.
 */



public class TipoClientes extends Generico {

    public TipoClientes(Context context)
    {

    super(context);
    try
        {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM TipoCliente",null);
        boolean aux = cursor.moveToFirst();
        while(aux)
            {
            TipoCliente tipoCliente = new TipoCliente(context,cursor.getInt(0));
            tipoClientes.add(tipoCliente);
            aux = cursor.moveToNext();
            }
        db.close();
        }
    catch (Exception e)
        {
        }

    }

    public TipoClientes(Context context,List<TipoCliente> tipoClientes)
    {
    super(context);
    this.tipoClientes = tipoClientes;
    }

    @Override
    public boolean modificar() {
        return false;
    }

    @Override
    public boolean cargar(){return true;}


    @Override
    public boolean guardar() {
        return false;
    }

    @Override
    public boolean eliminar() {
        return false;
    }

    @Override
    public boolean actualizar()
    {
    try
        {
        if(this.tipoClientes.size()>0)
            {
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL("DROP TABLE TipoCliente");
            db.execSQL("CREATE TABLE TipoCliente" +
                        "(" +
                        "id INTEGER," +
                        "tipoCliente TEXT" +
                        ")");
            boolean aux = true;
            for (int i=0;i<this.tipoClientes.size();i++)
                {
                aux &= this.tipoClientes.get(i).guardar();
                }
            db.close();
            return aux;
            }
        else
            {
            return false;
            }
        }
    catch (Exception e)
        {
        return false;
        }


    }

    private List<TipoCliente> tipoClientes = new ArrayList<TipoCliente>();
    public List<TipoCliente> getTipoClientes(){return tipoClientes;}


    @Override
    public Object getCopia() {
        return null;
    }

    @Override
    public void copiar(Object object) {
    }
}
