package com.federavesm.smapp.modelo.diaRepartidor.cargas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.federavesm.smapp.actividades.CodigosActividades;
import com.federavesm.smapp.modelo.Comunicador;
import com.federavesm.smapp.modelo.diaRepartidor.GenericoDiaRepartidorEvaluar;
import com.federavesm.smapp.modelo.diaRepartidor.GenericoDiaRepartidorProductos;
import com.federavesm.smapp.modelo.diaRepartidor.productos.Retornables;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.RepartoProductos;
import com.federavesm.smapp.modelo.servidor.datosXML.XML;

import java.util.Calendar;

/**
 * Created by Federico on 5/2/2018.
 */




public class Descarga extends GenericoDiaRepartidorProductos {



    public Descarga(Context context)
    {
        super(context);
    }



    private Retornables retornablesVacios = new Retornables();




    @Override
    public String getXMLToSend() {
        XML xml = new XML();

        xml.startTag("Descarga");


            xml.addTag("Hora",hora);

            xml.addValue(retornables.getXMLToSend());
            xml.addValue(descartables.getXMLToSend());

            xml.startTag("Vacios");
                xml.addValue(retornablesVacios.getXMLToSend());

            xml.closeTag("Vacios");



        xml.closeTag("Descarga");

        return xml.getXML();
    }












    @Override
    public boolean modificar() {
        return false;
    }

    private String hora;

    @Override
    public boolean guardar()
    {
    try
        {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues descarga = new ContentValues();
        descarga.put("idDiaRepartidor",this.idDiaRepartidor);
        descarga.put("bidones20L",this.retornables.getBidones20L());
        descarga.put("bidones20LVacios",this.retornablesVacios.getBidones20L());
        descarga.put("bidones12L",this.retornables.getBidones12L());
        descarga.put("bidones12LVacios",this.retornablesVacios.getBidones12L());
        descarga.put("bidones10L",this.descartables.getBidones10L());
        descarga.put("bidones8L",this.descartables.getBidones8L());
        descarga.put("bidones5L",this.descartables.getBidones5L());
        descarga.put("packBotellas2L",this.descartables.getPackBotellas2L());
        descarga.put("packBotellas500mL",this.descartables.getPackBotellas500mL());
        this.hora = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+ " : " + Calendar.getInstance().get(Calendar.MINUTE)+ " : " + Calendar.getInstance().get(Calendar.SECOND);
        descarga.put("hora",this.hora);

        boolean aux=true;
        if(db.insert("Descargas",null,descarga) > 0)
            {
            this.id = getLastId("Descargas");
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
        if(db.delete("Descargas", "id=" + "'" + this.id + "'", null)>0)
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
    return false;
    }




    @Override
    public String toString() {
        return this.hora ;
    }


    @Override
    public boolean evaluar()
    {
    incoherencia="";

    boolean aux = true;
    this.incoherencia="";

    if(Comunicador.getDiaRepartidor().getCargamento().getRetornables().getBidones20L() < this.getRetornables().getBidones20L())
    {
        aux=false;
        this.incoherencia+="No hay "+this.getRetornables().getBidones20L()+" bidones de 20L en el vehículo para descargar \n";
    }
    if(Comunicador.getDiaRepartidor().getCargamento().getRetornablesVacios().getBidones20L() < this.getRetornablesVacios().getBidones20L())
    {
        aux=false;
        this.incoherencia+="No hay "+this.getRetornablesVacios().getBidones20L()+" bidones de 20L vacios en el vehículo para descargar \n";
    }

    if(Comunicador.getDiaRepartidor().getCargamento().getRetornables().getBidones12L() < this.getRetornables().getBidones12L())
    {
        aux=false;
        this.incoherencia+="No hay "+this.getRetornables().getBidones12L()+" bidones de 12L en el vehículo para descargar \n";
    }
    if(Comunicador.getDiaRepartidor().getCargamento().getRetornablesVacios().getBidones12L() < this.getRetornablesVacios().getBidones12L())
    {
        aux=false;
        this.incoherencia+="No hay "+this.getRetornablesVacios().getBidones12L()+" bidones de 12L vacios en el vehículo para descargar \n";
    }
    if(Comunicador.getDiaRepartidor().getCargamento().getDescartables().getBidones10L() < this.getDescartables().getBidones10L())
    {
        aux=false;
        this.incoherencia+="No hay "+this.getDescartables().getBidones10L()+" bidones de 10L en el vehículo para descargar \n";
    }
    if(Comunicador.getDiaRepartidor().getCargamento().getDescartables().getBidones8L() < this.getDescartables().getBidones8L())
    {
        aux=false;
        this.incoherencia+="No hay "+this.getDescartables().getBidones8L()+" bidones de 8L en el vehículo para descargar \n";
    }
    if(Comunicador.getDiaRepartidor().getCargamento().getDescartables().getBidones5L() < this.getDescartables().getBidones5L())
    {
        aux=false;
        this.incoherencia+="No hay "+this.getDescartables().getBidones5L()+" bidones de 5L en el vehículo para descargar \n";
    }
    if(Comunicador.getDiaRepartidor().getCargamento().getDescartables().getPackBotellas2L() < this.getDescartables().getPackBotellas2L())
    {
        aux=false;
        this.incoherencia+="No hay "+this.getDescartables().getPackBotellas2L()+" pack de botellas de 2L en el vehículo para descargar \n";
    }
    if(Comunicador.getDiaRepartidor().getCargamento().getDescartables().getPackBotellas500mL() < this.getDescartables().getPackBotellas500mL())
    {
        aux=false;
        this.incoherencia+="No hay "+this.getDescartables().getPackBotellas500mL()+" pack de botellas de 500mL en el vehículo para descargar \n";
    }
    return aux;


    }





    @Override
    public String getEvaluar()
    {
        return this.incoherencia;
    }





    @Override
    public boolean getEstado()
    {
        if(this.retornables.getEstado() && this.descartables.getEstado() && this.retornablesVacios.getEstado())
        {
            if(this.retornables.have() || this.descartables.have() || this.retornablesVacios.have())
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
    public boolean cargar()
    {
    try
        {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Descargas WHERE id="+"'"+this.id+"'",null);
        boolean aux=false;
        if(cursor.moveToFirst())
            {
            aux = true;
            this.id = cursor.getInt(0);
            this.idDiaRepartidor = cursor.getInt(1);
            this.retornables.setBidones20L(cursor.getInt(2));
            this.retornablesVacios.setBidones20L(cursor.getInt(3));
            this.retornables.setBidones12L(cursor.getInt(4));
            this.retornablesVacios.setBidones12L(cursor.getInt(5));
            this.descartables.setBidones10L(cursor.getInt(6));
            this.descartables.setBidones8L(cursor.getInt(7));
            this.descartables.setBidones5L(cursor.getInt(8));
            this.descartables.setPackBotellas2L(cursor.getInt(9));
            this.descartables.setPackBotellas500mL(cursor.getInt(10));
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


    @Override
    public Object getCopia() {
        return null;
    }

    @Override
    public void copiar(Object object) {
    }


    public Retornables getRetornablesVacios() {
        return retornablesVacios;
    }

    public void setRetornablesVacios(Retornables retornablesVacios) {
        this.retornablesVacios = retornablesVacios;
    }



}
