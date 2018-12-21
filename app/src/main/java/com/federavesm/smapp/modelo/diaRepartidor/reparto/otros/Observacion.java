package com.federavesm.smapp.modelo.diaRepartidor.reparto.otros;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.federavesm.smapp.modelo.Comunicador;
import com.federavesm.smapp.modelo.Generico;
import com.federavesm.smapp.modelo.diaRepartidor.GenericoDiaRepartidor;
import com.federavesm.smapp.modelo.servidor.datosXML.XML;

/**
 * Created by Federico on 4/2/2018.
 */



public class Observacion extends GenericoDiaRepartidor {


    public Observacion(Context context)
    {
        super(context);
    }


    private String observacion="";


    public void limpiar()
    {
    this.observacion="";
    }



    @Override
    public void copiar(Object object)
    {
    try
        {
        Observacion observacion = (Observacion)object;
        this.id = observacion.getId();
        this.observacion = observacion.getObservacion();
        }
    catch (Exception e)
        {
        }
    }


    @Override
    public Object getCopia()
    {
    Observacion observacion = new Observacion(context);
    observacion.copiar(this);
    return observacion;
    }






    public String getXMLToSend()
    {
        XML xml = new XML();

        if(observacion.length()>0)
            {
            xml.addTag("Observacion",observacion);
            }



        return xml.getXML();
    }



    @Override
    public boolean cargar()
    {
    if(this.id>0)
        {
        try
            {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM Observaciones WHERE id=" + "'" + this.id + "'", null);
            boolean aux = false;
            if (cursor.moveToFirst())
                {
                aux = true;
                this.observacion = cursor.getString(1);
                }
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
    public boolean guardar()
    {
    try
        {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues observacion = new ContentValues();
        observacion.put("observacion",this.observacion);
        boolean aux = true;
        if(db.insert("Observaciones",null,observacion) > 0)
            {
            this.id = getLastId("Observaciones");
            }
        else
            {
            aux = false;
            }
        db.close();
        aux&= Comunicador.getReparto().modificar();
        return aux;
        }
    catch (Exception e)
        {
        return false;
        }
    }


    @Override
    public boolean modificar()
    {
    try
        {
        boolean aux = true;
        if (this.id > 0)
            {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues observacion = new ContentValues();
            observacion.put("observacion",this.observacion);
            String whereClause = "id=?";
            String whereArgs[] = {String.valueOf(this.id)};
            if (!(db.update("Observaciones", observacion, whereClause, whereArgs) > 0))
                {
                aux = false;
                }
            aux&= Comunicador.getReparto().modificar();
            }
        else
            {
            aux &= guardar();
            }
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
    if(this.id>0)
        {
            try
            {
                SQLiteDatabase db = getWritableDatabase();
                boolean aux = false;
                if(db.delete("Observaciones", "id=" + "'" + this.id + "'", null)>0)
                {
                    aux=true;
                }
                db.close();
                aux &= Comunicador.getReparto().modificar();
                return aux;
            }
            catch (Exception e)
            {
                return false;
            }
        }else{return true;}

    }
    @Override
    public boolean actualizar() {
        return false;
    }




    @Override
    public boolean getEstado() {
        if(this.observacion!=""){return true;}else{return false;}
    }



    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }


}