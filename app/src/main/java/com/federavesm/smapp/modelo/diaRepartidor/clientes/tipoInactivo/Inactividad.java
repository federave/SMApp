package com.federavesm.smapp.modelo.diaRepartidor.clientes.tipoInactivo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.federavesm.smapp.modelo.Generico;
import com.federavesm.smapp.modelo.diaRepartidor.GenericoDiaRepartidor;

/**
 * Created by Federico on 4/2/2018.
 */

public class Inactividad extends GenericoDiaRepartidor {


    public Inactividad(Context context)
    {
    super(context);
    this.tipoInactivo = new TipoInactivo(context);
    }






    private TipoInactivo tipoInactivo;

    private String ultimoConsumo;

    @Override
    public boolean modificar() {
        return false;
    }

    @Override
    public void copiar(Object object)
    {
    try
        {
        Inactividad inactividad = (Inactividad)object;
        this.id = inactividad.getId();
        this.tipoInactivo.copiar(inactividad.tipoInactivo);
        this.ultimoConsumo = inactividad.getUltimoConsumo();
        }
    catch (Exception e)
        {

        }
    }




    @Override
    public Object getCopia()
    {
    Inactividad inactividad = new Inactividad(context);
    inactividad.copiar(this);
    return inactividad;
    }




    @Override
    public boolean guardar()
    {
    try
        {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues inactividad = new ContentValues();
        inactividad.put("idInactividad",this.tipoInactivo.getId());
        inactividad.put("ultimoConsumo",this.ultimoConsumo);
        boolean aux = false;
        if(db.insert("Inactividad",null,inactividad) > 0)
            {
            this.id = getLastId("Inactividad");
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
    public boolean cargar()
    {
    try
        {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Inactividad WHERE id="+"'"+this.id+"'",null);
        boolean aux = true;
        if(cursor.moveToFirst())
            {
            this.tipoInactivo.setId(cursor.getInt(1));
            aux &= this.tipoInactivo.cargar();
            this.ultimoConsumo = cursor.getString(2);
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
    public boolean eliminar()
    {
    try
        {
        SQLiteDatabase db = getWritableDatabase();
        boolean aux = false;
        if(db.delete("Inactividad", "id=" + "'" + this.id + "'", null)>0)
            {
            aux = true;
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
    public boolean actualizar() {
        return false;
    }


    @Override
    public boolean getEstado() {
        return false;
    }


    public TipoInactivo getTipoInactivo() {
        return tipoInactivo;
    }

    public void setTipoInactivo(TipoInactivo tipoInactivo) {
        this.tipoInactivo = tipoInactivo;
    }

    public String getUltimoConsumo() {return ultimoConsumo;}

    public void setUltimoConsumo(String ultimoConsumo){this.ultimoConsumo = ultimoConsumo;}



}
