package com.federavesm.smapp.modelo.diaRepartidor.cargas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.federavesm.smapp.modelo.diaRepartidor.GenericoDiaRepartidorEvaluar;
import com.federavesm.smapp.modelo.diaRepartidor.GenericoDiaRepartidorProductos;
import com.federavesm.smapp.modelo.diaRepartidor.otros.Dispensers;
import com.federavesm.smapp.modelo.diaRepartidor.otros.Vertedores;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.RepartoProductos;
import com.federavesm.smapp.modelo.servidor.datosXML.XML;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Federico on 5/2/2018.
 */




public class Carga extends GenericoDiaRepartidorProductos {



    public Carga(Context context)
    {
    super(context);
    }



    private String hora;
    private Vertedores vertedores = new Vertedores();
    private Dispensers dispensers = new Dispensers();


    @Override
    public boolean modificar() {
        return false;
    }

    @Override
    public boolean guardar()
    {
    try
        {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues carga = new ContentValues();
        carga.put("idDiaRepartidor",this.idDiaRepartidor);
        carga.put("bidones20L",this.retornables.getBidones20L());
        carga.put("bidones12L",this.retornables.getBidones12L());
        carga.put("bidones10L",this.descartables.getBidones10L());
        carga.put("bidones8L",this.descartables.getBidones8L());
        carga.put("bidones5L",this.descartables.getBidones5L());
        carga.put("packBotellas2L",this.descartables.getPackBotellas2L());
        carga.put("packBotellas500mL",this.descartables.getPackBotellas500mL());
        carga.put("vertedores",this.vertedores.getCantidad());
        carga.put("dispensers",this.dispensers.getCantidad());

        this.hora = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+ " : " + Calendar.getInstance().get(Calendar.MINUTE)+ " : " + Calendar.getInstance().get(Calendar.SECOND);
        carga.put("hora",this.hora);

        boolean aux=true;
        if(db.insert("Cargas",null,carga) > 0)
            {
            this.id = getLastId("Cargas");
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
        if(db.delete("Cargas", "id=" + "'" + this.id + "'", null)>0)
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

        ContentValues carga = new ContentValues();
        carga.put("bidones20L",this.retornables.getBidones20L());
        carga.put("bidones12L",this.retornables.getBidones12L());
        carga.put("bidones10L",this.descartables.getBidones10L());
        carga.put("bidones8L",this.descartables.getBidones8L());
        carga.put("bidones5L",this.descartables.getBidones5L());
        carga.put("packBotellas2L",this.descartables.getPackBotellas2L());
        carga.put("packBotellas500mL",this.descartables.getPackBotellas500mL());
        carga.put("vertedores",this.vertedores.getCantidad());
        carga.put("dispensers",this.dispensers.getCantidad());

        this.hora = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+ " : " + Calendar.getInstance().get(Calendar.MINUTE)+ " : " + Calendar.getInstance().get(Calendar.SECOND);
        carga.put("hora",this.hora);

        String whereClause = "id=?";
        String whereArgs[] = {String.valueOf(this.id)};

        boolean aux=true;
        if(!(db.update("Cargas", carga, whereClause, whereArgs)> 0))
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
    public boolean evaluar()
    {
    if(this.retornables.getEstado() && this.descartables.getEstado())
        {
        if(this.retornables.have() || this.descartables.have())
            {
            return true;
            }
        else
            {
            return false;
            }
        }
    else
        {
        return false;
        }
    }

    @Override
    public String getEvaluar() {
        return "";
    }


    @Override
    public String getXMLToSend() {
        XML xml = new XML();

        xml.startTag("Carga");
            xml.addTag("Hora",hora);
            xml.addValue(retornables.getXMLToSend());
            xml.addValue(descartables.getXMLToSend());
            xml.addValue(vertedores.getXMLToSend());
            xml.addValue(dispensers.getXMLToSend());
        xml.closeTag("Carga");

        return xml.getXML();
    }




    @Override
    public boolean getEstado() {
        return false;
    }

    @Override
    public boolean cargar()
    {
    try
        {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Cargas WHERE id="+"'"+this.id+"'",null);
        boolean aux=false;
        if(cursor.moveToFirst())
            {
            aux = true;
            this.id = cursor.getInt(0);
            this.idDiaRepartidor = cursor.getInt(1);
            this.retornables.setBidones20L(cursor.getInt(2));
            this.retornables.setBidones12L(cursor.getInt(3));
            this.descartables.setBidones10L(cursor.getInt(4));
            this.descartables.setBidones8L(cursor.getInt(5));
            this.descartables.setBidones5L(cursor.getInt(6));
            this.descartables.setPackBotellas2L(cursor.getInt(7));
            this.descartables.setPackBotellas500mL(cursor.getInt(8));
            this.vertedores.setCantidad(cursor.getInt(9));
            this.dispensers.setCantidad(cursor.getInt(10));
            this.hora = cursor.getString(11);
            }
        db.close();
        return aux;
        }
    catch (Exception e)
        {
        String x=e.toString();
        return false;
        }
    }


    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Vertedores getVertedores() {
        return vertedores;
    }

    public void setVertedores(Vertedores vertedores) {
        this.vertedores = vertedores;
    }

    public Dispensers getDispensers() {
        return dispensers;
    }

    public void setDispensers(Dispensers dispensers) {
        this.dispensers = dispensers;
    }

    @Override
    public String toString() {
        return this.hora ;
    }


    @Override
    public Object getCopia()
    {
    Carga carga = new Carga(this.context);
    carga.copiar(this);
    return carga;
    }


    @Override
    public void copiar(Object object)
    {
    try
        {
        Carga carga = (Carga)object;

        this.id = carga.getId();
        this.idDiaRepartidor = carga.getIdDiaRepartidor();
        this.retornables.copiar(carga.getRetornables());
        this.descartables.copiar(carga.getDescartables());
        this.vertedores.copiar(carga.getVertedores());
        this.dispensers.copiar(carga.getDispensers());
        this.hora = carga.getHora();
        }
    catch (Exception e)
        {

        }


    }

}
