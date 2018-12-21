package com.federavesm.smapp.modelo.diaRepartidor.repartidores;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.federavesm.smapp.modelo.Generico;
import com.federavesm.smapp.modelo.diaRepartidor.repartidores.Repartidor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Federico on 2/1/2018.
 */

public class Repartidores extends Generico {

    public Repartidores(Context context)
    {

    super(context);
    try
        {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Repartidores",null);
        boolean aux = cursor.moveToFirst();
        while(aux)
            {
            Repartidor repartidor = new Repartidor(context,cursor.getInt(0));
            repartidores.add(repartidor);
            aux = cursor.moveToNext();
            }
        db.close();
        }
    catch (Exception e)
        {

        }
    }

    public Repartidores(Context context,List<Repartidor> repartidores)
    {
    super(context);
    this.repartidores = repartidores;
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
    public boolean actualizar() {

    try
        {
        if(this.repartidores.size()>0)
            {
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL("DROP TABLE Repartidores");
            db.execSQL("CREATE TABLE Repartidores" +
                    "(" +
                    "id INTEGER," +
                    "nombre TEXT," +
                    "apellido TEXT," +
                    "dni TEXT" +
                    ")");
            boolean aux = true;
            for (int i=0;i<this.repartidores.size();i++)
                {
                aux &= this.repartidores.get(i).guardar();
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

    private List<Repartidor> repartidores = new ArrayList<Repartidor>();
    public List<Repartidor> getRepartidores(){return repartidores;}


    @Override
    public Object getCopia() {
        return null;
    }

    @Override
    public void copiar(Object object) {
    }
}
