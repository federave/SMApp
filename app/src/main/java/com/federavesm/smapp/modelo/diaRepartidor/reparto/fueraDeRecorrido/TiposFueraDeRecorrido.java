package com.federavesm.smapp.modelo.diaRepartidor.reparto.fueraDeRecorrido;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.federavesm.smapp.modelo.Generico;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Federico on 2/5/2018.
 */



public class TiposFueraDeRecorrido extends Generico {

    public TiposFueraDeRecorrido(Context context)
    {

        super(context);
        try
        {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM TipoFueraDeRecorrido",null);
            boolean aux = cursor.moveToFirst();
            while(aux)
            {
                TipoFueraDeRecorrido tipoFueraDeRecorrido = new TipoFueraDeRecorrido(context,cursor.getInt(0));
                tiposFueraDeRecorrido.add(tipoFueraDeRecorrido);
                aux = cursor.moveToNext();
            }
            db.close();
        }
        catch (Exception e)
        {
        }

    }

    public TiposFueraDeRecorrido(Context context,List<TipoFueraDeRecorrido> tiposFueraDeRecorrido)
    {
        super(context);
        this.tiposFueraDeRecorrido = tiposFueraDeRecorrido;
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
            if(this.tiposFueraDeRecorrido.size()>0)
            {
                SQLiteDatabase db = getWritableDatabase();
                db.execSQL("DROP TABLE TipoFueraDeRecorrido");
                db.execSQL("CREATE TABLE TipoFueraDeRecorrido" +
                        "(" +
                        "id INTEGER," +
                        "tipoFueraDeRecorrido TEXT" +
                        ")");
                boolean aux = true;
                for (int i=0;i<this.tiposFueraDeRecorrido.size();i++)
                {
                    aux &= this.tiposFueraDeRecorrido.get(i).guardar();
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

    private List<TipoFueraDeRecorrido> tiposFueraDeRecorrido = new ArrayList<TipoFueraDeRecorrido>();
    public List<TipoFueraDeRecorrido> getTiposFueraDeRecorrido(){return tiposFueraDeRecorrido;}


    @Override
    public Object getCopia() {
        return null;
    }

    @Override
    public void copiar(Object object) {
    }
}
