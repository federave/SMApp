package com.federavesm.smapp.modelo.diaRepartidor.reparto.vendedor;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.federavesm.smapp.modelo.Generico;
import com.federavesm.smapp.modelo.diaRepartidor.repartidores.Repartidor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Federico on 13/2/2018.
 */




public class Vendedores extends Generico {

    public Vendedores(Context context)
    {
    super(context);
    try
        {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Vendedores",null);
        boolean aux = cursor.moveToFirst();
        while(aux)
            {
            Vendedor vendedor = new Vendedor(context,cursor.getInt(0));
            vendedores.add(vendedor);
            aux = cursor.moveToNext();
            }
        db.close();
        }
    catch (Exception e)
        {
        }
    }

    public Vendedores(Context context,List<Vendedor> vendedores)
    {
    super(context);
    this.vendedores = vendedores;
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
        if(this.vendedores.size()>0)
            {
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL("DROP TABLE Vendedores");
            db.execSQL("CREATE TABLE Vendedores" +
                    "(" +
                    "id INTEGER," +
                    "nombre TEXT," +
                    "apellido TEXT," +
                    "dni TEXT" +
                    ")");
            boolean aux=true;
            for (int i=0;i<this.vendedores.size();i++)
                {
                aux &= this.vendedores.get(i).guardar();
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

    private List<Vendedor> vendedores = new ArrayList<Vendedor>();
    public List<Vendedor> getVendedores(){return vendedores;}


    @Override
    public Object getCopia() {
        return null;
    }

    @Override
    public void copiar(Object object) {
    }
}
