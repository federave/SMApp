package com.federavesm.smapp.modelo.diaRepartidor.productos;

import com.federavesm.smapp.modelo.diaRepartidor.reparto.alquiler.Alquiler;
import com.federavesm.smapp.modelo.servidor.datosXML.XML;

/**
 * Created by Federico on 12/2/2018.
 */

public class Alquileres
{

    public Alquileres(){}



    private int alquileres6Bidones=0;
    private int alquileres8Bidones=0;
    private int alquileres10Bidones=0;
    private int alquileres12Bidones=0;




    public String getXMLToSend()
    {

    XML xml = new XML();
    xml.addTag("Alquiler6Bidones",String.valueOf(this.alquileres6Bidones));
    xml.addTag("Alquiler8Bidones",String.valueOf(this.alquileres8Bidones));
    xml.addTag("Alquiler10Bidones",String.valueOf(this.alquileres10Bidones));
    xml.addTag("Alquiler12Bidones",String.valueOf(this.alquileres12Bidones));
    return xml.getXML();
    }


    public String getDatos()
    {


        String aux = "";
        if(this.alquileres6Bidones>0)
            aux+= "\nAlquiler6Bidones: "+String.valueOf(this.alquileres6Bidones);
        if(this.alquileres8Bidones>0)
            aux+= "\nAlquiler8Bidones: "+String.valueOf(this.alquileres8Bidones);

        if(this.alquileres10Bidones>0)
            aux+= "\nAlquiler10Bidones: "+String.valueOf(this.alquileres10Bidones);

        if(this.alquileres12Bidones>0)
            aux+= "\nAlquiler12Bidones: "+String.valueOf(this.alquileres12Bidones);






        return aux;
    }




















    public boolean getAlquileresBidones20L()
    {
    if(alquileres6Bidones>0 || alquileres8Bidones>0)return true;
    else return false;
    }

    public boolean getAlquileresBidones12L()
    {
    if(alquileres10Bidones>0 || alquileres12Bidones>0)return true;
    else return false;
    }




    public int getAlquileres6Bidones() {
        return alquileres6Bidones;
    }

    public void setAlquileres6Bidones(int alquileres6Bidones) {
        this.alquileres6Bidones = alquileres6Bidones;
    }

    public int getAlquileres8Bidones() {
        return alquileres8Bidones;
    }

    public void setAlquileres8Bidones(int alquileres8Bidones) {
        this.alquileres8Bidones = alquileres8Bidones;
    }

    public int getAlquileres10Bidones() {
        return alquileres10Bidones;
    }

    public void setAlquileres10Bidones(int alquileres10Bidones) {
        this.alquileres10Bidones = alquileres10Bidones;
    }

    public int getAlquileres12Bidones() {
        return alquileres12Bidones;
    }

    public void setAlquileres12Bidones(int alquileres12Bidones) {
        this.alquileres12Bidones = alquileres12Bidones;
    }


    public boolean getEstado()
    {
        if(this.alquileres6Bidones>0 || this.alquileres8Bidones>0 || this.alquileres10Bidones>0 || this.alquileres12Bidones>0 )
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
        if(this.alquileres6Bidones>0 || this.alquileres8Bidones>0 || this.alquileres10Bidones>0 || this.alquileres12Bidones>0 )
        {
            return true;
        }
        else
        {
            return false;
        }
    }







    public Object getCopia()
    {
    Alquileres alquileres = new Alquileres();
    alquileres.setAlquileres6Bidones(this.alquileres6Bidones);
    alquileres.setAlquileres8Bidones(this.alquileres8Bidones);
    alquileres.setAlquileres10Bidones(this.alquileres10Bidones);
    alquileres.setAlquileres12Bidones(this.alquileres12Bidones);
    return alquileres;
    }

    public void copiar(Object object)
    {
    try
        {
        Alquileres alquileres = (Alquileres)object;
        this.alquileres6Bidones = alquileres.getAlquileres6Bidones();
        this.alquileres8Bidones = alquileres.getAlquileres8Bidones();
        this.alquileres10Bidones = alquileres.getAlquileres10Bidones();
        this.alquileres12Bidones = alquileres.getAlquileres12Bidones();
        }
    catch (Exception e)
        {
        }
    }




}
