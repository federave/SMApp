package com.federavesm.smapp.modelo.diaRepartidor.productos;

import com.federavesm.smapp.modelo.servidor.datosXML.XML;

/**
 * Created by Federico on 4/2/2018.
 */

public class Retornables {



    public Retornables(){}

    protected int bidones20L=0;
    protected int bidones12L=0;


    public int getBidones20L() {
        return bidones20L;
    }

    public void setBidones20L(int bidones20L) {
        this.bidones20L = bidones20L;
    }

    public int getBidones12L() {
        return bidones12L;
    }

    public void setBidones12L(int bidones12L) {
        this.bidones12L = bidones12L;
    }


    public String getXMLToSend()
    {
    XML xml = new XML();
    xml.addTag("Bidones20L",String.valueOf(this.bidones20L));
    xml.addTag("Bidones12L",String.valueOf(this.bidones12L));
    return xml.getXML();
    }



    public boolean getEstado()
    {
    if(this.bidones20L >= 0 && this.bidones12L >=0)
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
    if(this.bidones20L > 0 || this.bidones12L > 0)
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
        Retornables retornables = (Retornables)object;
        this.bidones20L = retornables.getBidones20L();
        this.bidones12L=retornables.getBidones12L();
        }
    catch (Exception e)
        {
        }
    }


    public Object getCopia()
    {
    Retornables retornables = new Retornables();
    retornables.setBidones20L(this.bidones20L);
    retornables.setBidones12L(this.bidones12L);
    return retornables;
    }

}
