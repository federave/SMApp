package com.federavesm.smapp.modelo.diaRepartidor.clientes.tipoInactivo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.federavesm.smapp.modelo.Fecha;
import com.federavesm.smapp.modelo.diaRepartidor.GenericoDiaRepartidor;

/**
 * Created by Federico on 4/2/2018.
 */

public class EstadoInactividad extends GenericoDiaRepartidor {


    public EstadoInactividad(Context context)
    {
    super(context);
    this.tipoInactivo = new TipoInactivo(context);
    }


    private Fecha fecha;
    private int idCliente;


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
        EstadoInactividad estadoInactividad = (EstadoInactividad)object;
        this.id = estadoInactividad.getId();
        this.tipoInactivo.copiar(estadoInactividad.tipoInactivo);
        this.ultimoConsumo = estadoInactividad.getUltimoConsumo();
        }
    catch (Exception e)
        {

        }
    }




    @Override
    public Object getCopia()
    {
    EstadoInactividad estadoInactividad = new EstadoInactividad(context);
    estadoInactividad.copiar(this);
    return estadoInactividad;
    }




    @Override
    public boolean guardar()
    {
    try
        {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues estadoInactividad = new ContentValues();
        estadoInactividad.put("idCliente",this.idCliente);
        estadoInactividad.put("idInactividad",this.tipoInactivo.getId());
        estadoInactividad.put("ultimoConsumo",this.ultimoConsumo);
        estadoInactividad.put("fecha",this.fecha.toString());

        boolean aux = false;
        if(db.insert("EstadoInactividad",null,estadoInactividad) > 0)
            {
            this.id = getLastId("EstadoInactividad");
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
            Cursor cursor = db.rawQuery("SELECT * FROM EstadoInactividad WHERE idCliente="+"'"+this.idCliente+"'" + " AND fecha="+"'"+this.fecha.toString()+"'",null);
            boolean aux = false;
            if(cursor.moveToFirst())
            {
                aux = true;
                this.id = cursor.getInt(0);
                this.tipoInactivo.setId(cursor.getInt(2));
                this.tipoInactivo.cargar();
                this.ultimoConsumo = cursor.getString(3);
            }
            db.close();
            return aux;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public boolean cargar(Fecha fecha)
    {
        this.fecha=fecha;
        try
        {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM EstadoInactividad WHERE idCliente="+"'"+this.idCliente+"'" + " AND fecha<="+"'"+this.fecha.toString()+"'"+" ORDER BY fecha DESC",null);
            boolean aux = false;
            if(cursor.moveToFirst())
            {
                aux = true;
                this.id = cursor.getInt(0);
                this.tipoInactivo.setId(cursor.getInt(2));
                this.tipoInactivo.cargar();
                this.ultimoConsumo = cursor.getString(3);
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
        if(db.delete("EstadoInactividad", "id=" + "'" + this.id + "'", null)>0)
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
    public boolean actualizar()
    {
        try
        {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues estadoInactividad = new ContentValues();
            estadoInactividad.put("idCliente",this.idCliente);
            estadoInactividad.put("idInactividad",this.tipoInactivo.getId());
            estadoInactividad.put("ultimoConsumo",this.ultimoConsumo);
            estadoInactividad.put("fecha",this.fecha.toString());

            boolean aux = false;
            if(db.insert("EstadoInactividad",null,estadoInactividad) > 0)
            {
                this.id = getLastId("EstadoInactividad");
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

    public Fecha getFecha() {
        return fecha;
    }

    public void setFecha(Fecha fecha) {
        this.fecha = fecha;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }


}
