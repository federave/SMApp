package com.federavesm.smapp.modelo.diaRepartidor.reparto.fueraDeRecorrido;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.federavesm.smapp.R;
import com.federavesm.smapp.modelo.Convertidor;
import com.federavesm.smapp.modelo.diaRepartidor.GenericoDiaRepartidor;

/**
 * Created by Federico on 11/2/2018.
 */




public class FueraDeRecorrido extends GenericoDiaRepartidor {


    public FueraDeRecorrido(Context context)
    {
    super(context);
    this.tipoFueraDeRecorrido = new TipoFueraDeRecorrido(context);
    }





    private String mensaje;

    private TipoFueraDeRecorrido tipoFueraDeRecorrido;

    public TipoFueraDeRecorrido getTipoFueraDeRecorrido() {
        return tipoFueraDeRecorrido;
    }

    public void setTipoFueraDeRecorrido(TipoFueraDeRecorrido tipoFueraDeRecorrido) {
        this.tipoFueraDeRecorrido = tipoFueraDeRecorrido;
    }


    private boolean estado=false;

    public void setEstado(boolean estado) {
        this.estado = estado;
    }


    @Override
    public void copiar(Object object)
    {
    try
        {
        FueraDeRecorrido fueraDeRecorrido = (FueraDeRecorrido)object;
        this.id = fueraDeRecorrido.getId();
        this.mensaje = fueraDeRecorrido.getMensaje();
        this.tipoFueraDeRecorrido = fueraDeRecorrido.getTipoFueraDeRecorrido();
        }
    catch (Exception e)
        {
        }
    }


    @Override
    public Object getCopia()
    {
    FueraDeRecorrido fueraDeRecorrido = new FueraDeRecorrido(context);
    fueraDeRecorrido.copiar(this);
    return fueraDeRecorrido;
    }





    @Override
    public boolean modificar() {
        return false;
    }






    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }



    @Override
    public boolean cargar()
    {
    if(this.id > 0)
        {
        try
            {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM FueraDeRecorrido WHERE id="+"'"+this.id+"'",null);
            boolean aux = false;
            if(cursor.moveToFirst())
                {
                aux = true;
                this.tipoFueraDeRecorrido = new TipoFueraDeRecorrido(context,cursor.getInt(1));
                this.mensaje = cursor.getString(2);
                }
            db.close();
            return aux;
            }
        catch (Exception e)
            {
            return  false;
            }
        }
    else
        {
        return true;
        }

    }


    @Override
    public boolean guardar()
    {
    try
        {
        boolean aux = true;
        SQLiteDatabase db = getWritableDatabase();
        ContentValues reparto = new ContentValues();
        reparto.put("idTipoFueraDeRecorrido",this.tipoFueraDeRecorrido.getId());
        reparto.put("mensaje",this.mensaje);

        if(db.insert("FueraDeRecorrido",null,reparto) > 0)
            {
            this.id=getLastId("FueraDeRecorrido");
            }
        else
            {
            aux = false;
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
        if(db.delete("FueraDeRecorrido", "id=" + "'" + this.id + "'", null)>0)
            {
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
    public boolean actualizar() {
        return false;
    }


    @Override
    public boolean getEstado()
    {
    if(this.tipoFueraDeRecorrido.getId()>0)
    return true;
    else
    return false;
    }


    public int getRecursoImagen() {


        if (this.id > 0) {
            return R.drawable.llamado;
        } else {
            return -1;
        }
    }



}