package com.federavesm.smapp.modelo.diaRepartidor.productos;

import com.federavesm.smapp.modelo.servidor.datosXML.XML;

/**
 * Created by Federico on 4/2/2018.
 */



public class Descartables {


    public Descartables(){}

    protected int bidones10L=0;
    protected int bidones8L=0;
    protected int bidones5L=0;
    protected int packBotellas2L=0;
    protected int packBotellas500mL=0;




    public String getXMLToSend()
    {

    XML xml = new XML();
    xml.addTag("Bidones10L",String.valueOf(this.bidones10L));
    xml.addTag("Bidones8L",String.valueOf(this.bidones8L));
    xml.addTag("Bidones5L",String.valueOf(this.bidones5L));
    xml.addTag("PackBotellas2L",String.valueOf(this.packBotellas2L));
    xml.addTag("PackBotellas500mL",String.valueOf(this.packBotellas500mL));

    return xml.getXML();
    }


    public String getDatos()
    {
        String aux = "";
        if(this.bidones10L>0)
            aux+= "\nBidones10L: "+String.valueOf(this.bidones10L);

        if(this.bidones8L>0)
            aux+= "\nBidones8L: "+String.valueOf(this.bidones8L);

        if(this.bidones5L>0)
            aux+= "\nBidones5L: "+String.valueOf(this.bidones5L);


        if(this.packBotellas2L>0)
            aux+= "\nPackBotellas2L: "+String.valueOf(this.packBotellas2L);


        if(this.packBotellas500mL>0)
            aux+= "\nPackBotellas500mL: "+String.valueOf(this.packBotellas500mL);




        return aux;

    }














    public boolean getEstado()
    {
    if(this.bidones10L >= 0 && this.bidones8L >=0 && this.bidones5L >=0 && this.packBotellas2L >=0 && this.packBotellas500mL >=0)
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
    if(this.bidones10L > 0 || this.bidones8L > 0 || this.bidones5L > 0 || this.packBotellas2L > 0 || this.packBotellas500mL > 0)
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
        Descartables descartables = (Descartables)object;
        this.bidones10L = descartables.getBidones10L();
        this.bidones8L=descartables.getBidones8L();
        this.bidones5L=descartables.getBidones5L();
        this.packBotellas2L=descartables.getPackBotellas2L();
        this.packBotellas500mL=descartables.getPackBotellas500mL();
        }
    catch (Exception e)
        {
        }
    }


    public Object getCopia()
    {
    Descartables descartables = new Descartables();
    descartables.setBidones10L(this.bidones10L);
    descartables.setBidones8L(this.bidones8L);
    descartables.setBidones5L(this.bidones5L);
    descartables.setPackBotellas2L(this.packBotellas2L);
    descartables.setPackBotellas500mL(this.packBotellas500mL);
    return descartables;
    }





    public int getBidones10L() {
        return bidones10L;
    }

    public void setBidones10L(int bidones10L) {
        this.bidones10L = bidones10L;
    }

    public int getBidones8L() {
        return bidones8L;
    }

    public void setBidones8L(int bidones8L) {
        this.bidones8L = bidones8L;
    }

    public int getBidones5L() {
        return bidones5L;
    }

    public void setBidones5L(int bidones5L) {
        this.bidones5L = bidones5L;
    }

    public int getPackBotellas2L() {
        return packBotellas2L;
    }

    public void setPackBotellas2L(int packBotellas2L) {
        this.packBotellas2L = packBotellas2L;
    }

    public int getPackBotellas500mL() {
        return packBotellas500mL;
    }

    public void setPackBotellas500mL(int packBotellas500mL) {
        this.packBotellas500mL = packBotellas500mL;
    }




}