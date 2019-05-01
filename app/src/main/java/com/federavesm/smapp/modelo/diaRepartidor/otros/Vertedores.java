package com.federavesm.smapp.modelo.diaRepartidor.otros;

import com.federavesm.smapp.modelo.servidor.datosXML.XML;




public class Vertedores {



    public Vertedores(){}

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
        xml.addTag("Vertedores",String.valueOf(this.cantidad));
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
            Vertedores vertedores = (Vertedores)object;
            this.cantidad = vertedores.getCantidad();
        }
        catch (Exception e)
        {
        }
    }


    public Object getCopia()
    {
        Vertedores vertedores = new Vertedores();
        vertedores.setCantidad(this.cantidad);
        return vertedores;
    }

}
