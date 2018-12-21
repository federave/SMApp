package com.federavesm.smapp.modelo.diaRepartidor.reparto.otros;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.federavesm.smapp.modelo.Comunicador;
import com.federavesm.smapp.modelo.diaRepartidor.GenericoDiaRepartidorEvaluar;
import com.federavesm.smapp.modelo.diaRepartidor.productos.Retornables;
import com.federavesm.smapp.modelo.servidor.datosXML.XML;

/**
 * Created by Federico on 4/2/2018.
 */

public class Vacios extends GenericoDiaRepartidorEvaluar {



    public Vacios(Context context)
    {
        super(context);
    }



    private Retornables retornables = new Retornables();



    public void limpiar()
    {
    this.retornables = new Retornables();
    }

    public boolean have()
    {
    return this.retornables.have();
    }


    @Override
    public void copiar(Object object)
    {
    try
        {
        Vacios vacios = (Vacios)object;
        this.id = vacios.getId();
        this.retornables.copiar(vacios.getRetornables());
        }
    catch (Exception e)
        {
        }
    }


    @Override
    public Object getCopia()
    {
    Vacios vacios = new Vacios(context);
    vacios.copiar(this);
    return vacios;
    }


    @Override
    public String getXMLToSend() {

        XML xml = new XML();

        if(this.retornables.have())
            {
            xml.startTag("Vacios");
                xml.addValue(retornables.getXMLToSend());
            xml.closeTag("Vacios");
            }

        return xml.getXML();
    }





    @Override
    public boolean guardar()
    {
    try
        {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues vacios = new ContentValues();
        vacios.put("bidones20L",this.getRetornables().getBidones20L());
        vacios.put("bidones12L",this.getRetornables().getBidones12L());
        boolean aux = true;
        if(db.insert("Vacios",null,vacios) > 0)
            {
            this.id = getLastId("Vacios");
            }
        else
            {
            aux = false;
            }
        db.close();
        aux &= Comunicador.getReparto().modificar();
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
            ContentValues vacios = new ContentValues();
            vacios.put("bidones20L",this.getRetornables().getBidones20L());
            vacios.put("bidones12L",this.getRetornables().getBidones12L());
            String whereClause = "id=?";
            String whereArgs[] = {String.valueOf(this.id)};
            if (!(db.update("Vacios", vacios, whereClause, whereArgs) > 0))
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
    if(this.id > 0)
        {
        try
            {
            SQLiteDatabase db = getWritableDatabase();
            boolean aux=false;
            if(db.delete("Vacios", "id=" + "'" + this.id + "'", null)>0)
                {
                aux=true;
                }
            db.close();
            this.id = -1;
            Comunicador.getReparto().modificar();
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
    public boolean cargar()
    {
    if(this.id > 0)
        {
        try
            {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM Vacios WHERE id="+"'"+this.id+"'",null);
            boolean aux=false;
            if(cursor.moveToFirst())
                {
                aux=true;
                this.retornables.setBidones20L(cursor.getInt(1));
                this.retornables.setBidones12L(cursor.getInt(2));
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
    public boolean actualizar() {
        return false;
    }




    @Override
    public boolean evaluar() {
        return false;
    }

    @Override
    public String getEvaluar() {
        return "";
    }



    @Override
    public boolean getEstado()
    {
    return this.retornables.getEstado();
    }




    public Retornables getRetornables() {
        return retornables;
    }

    public void setRetornables(Retornables retornables) {
        this.retornables = retornables;
    }



}
