package com.federavesm.smapp.modelo.diaRepartidor.otros;

import com.federavesm.smapp.modelo.servidor.datosXML.XML;



public class Dispensers {



    public Dispensers(){}

    protected int cantidad=0;

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getXMLToSend()
    {
        XML xml = new XML();
        xml.addTag("Dispensers",String.valueOf(this.cantidad));
        return xml.getXML();
    }



    public boolean getEstado()
    {
        if(this.cantidad >= 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }


    public boolean have()
    {
        if(this.cantidad > 0 )
        {
            return true;
        }
        else
        {
            return false;
        }
    }


    public void copiar(Object object)
    {
        try
        {
            Dispensers dispensers = (Dispensers)object;
            this.cantidad = dispensers.getCantidad();
        }
        catch (Exception e)
        {
        }
    }


    public Object getCopia()
    {
        Dispensers dispensers = new Dispensers();
        dispensers.setCantidad(this.cantidad);
        return dispensers;
    }

}
