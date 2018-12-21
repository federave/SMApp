package com.federavesm.smapp.modelo.diaRepartidor.clientes.tipoInactivo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.federavesm.smapp.modelo.Generico;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Federico on 13/2/2018.
 */





public class TipoInactivos extends Generico {

    public TipoInactivos(Context context)
    {
    super(context);
    try
        {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM TipoInactivo",null);
        boolean aux = cursor.moveToFirst();
        while(aux)
            {
            TipoInactivo tipoInactivo = new TipoInactivo(context,cursor.getInt(0));
            tipoInactivos.add(tipoInactivo);
            aux = cursor.moveToNext();
            }
        db.close();
        }
    catch (Exception e)
        {

        }
    }

    public TipoInactivos(Context context,List<TipoInactivo> tipoInactivos)
    {
    super(context);
    this.tipoInactivos = tipoInactivos;
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
        if(this.tipoInactivos.size()>0)
            {
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL("DROP TABLE TipoInactivo");
            db.execSQL("CREATE TABLE TipoInactivo" +
                    "(" +
                    "id INTEGER," +
                    "tipoInactivo TEXT" +
                    ")");
            boolean aux = true;
            for (int i=0;i<this.tipoInactivos.size();i++)
                {
                aux &= this.tipoInactivos.get(i).guardar();
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

    private List<TipoInactivo> tipoInactivos = new ArrayList<TipoInactivo>();
    public List<TipoInactivo> getTipoInactivos(){return tipoInactivos;}


    @Override
    public Object getCopia() {
        return null;
    }

    @Override
    public void copiar(Object object) {
    }
}
