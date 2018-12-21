package com.federavesm.smapp.modelo.diaRepartidor.reparto.tipoVisita;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.federavesm.smapp.modelo.Generico;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Federico on 13/2/2018.
 */


public class TipoVisitas extends Generico {

    public TipoVisitas(Context context)
    {
    super(context);
    try
        {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM TipoVisita",null);
        boolean aux = cursor.moveToFirst();
        while(aux)
            {
            TipoVisita tipoVisita = new TipoVisita(context,cursor.getInt(0));
            tipoVisitas.add(tipoVisita);
            aux = cursor.moveToNext();
            }
        db.close();
        }
    catch (Exception e)
        {
        }
    }

    public TipoVisitas(Context context,List<TipoVisita> tipoVisitas)
    {
    super(context);
    this.tipoVisitas = tipoVisitas;
    }


    @Override
    public boolean cargar(){return true;}

    @Override
    public boolean modificar() {
        return false;
    }

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
        if(this.tipoVisitas.size()>0)
            {
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL("DROP TABLE TipoVisita");
            db.execSQL("CREATE TABLE TipoVisita" +
                        "(" +
                        "id INTEGER," +
                        "tipoVisita TEXT" +
                        ")");
            boolean aux = true;
            for (int i=0;i<this.tipoVisitas.size();i++)
                {
                aux&=this.tipoVisitas.get(i).guardar();
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

    private List<TipoVisita> tipoVisitas = new ArrayList<TipoVisita>();
    public List<TipoVisita> getTipoVisitas(){return tipoVisitas;}


    @Override
    public Object getCopia() {
        return null;
    }

    @Override
    public void copiar(Object object) {
    }
}
