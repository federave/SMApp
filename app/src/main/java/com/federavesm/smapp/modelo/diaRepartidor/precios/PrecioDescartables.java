package com.federavesm.smapp.modelo.diaRepartidor.precios;

/**
 * Created by Federico on 4/2/2018.
 */

public class PrecioDescartables {



    public PrecioDescartables(){}

    protected float bidon10L=0;
    protected float bidon8L=0;
    protected float bidon5L=0;
    protected float packBotellas2L=0;
    protected float packBotellas500mL=0;


    public float getBidon10L() {
        return bidon10L;
    }

    public void setBidon10L(float bidon10L) {
        this.bidon10L = bidon10L;
    }

    public float getBidon8L() {
        return bidon8L;
    }

    public void setBidon8L(float bidon8L) {
        this.bidon8L = bidon8L;
    }

    public float getBidon5L() {
        return bidon5L;
    }

    public void setBidon5L(float bidon5L) {
        this.bidon5L = bidon5L;
    }

    public float getPackBotellas2L() {
        return packBotellas2L;
    }

    public void setPackBotellas2L(float packBotellas2L) {
        this.packBotellas2L = packBotellas2L;
    }

    public float getPackBotellas500mL() {
        return packBotellas500mL;
    }

    public void setPackBotellas500mL(float packBotellas500mL) {
        this.packBotellas500mL = packBotellas500mL;
    }


    public void copiar(Object object)
    {
    try
        {
        PrecioDescartables precioDescartables = (PrecioDescartables)object;
        this.bidon10L = precioDescartables.getBidon10L();
        this.bidon8L = precioDescartables.getBidon8L();
        this.bidon5L = precioDescartables.getBidon5L();
        this.packBotellas2L = precioDescartables.getPackBotellas2L();
        this.packBotellas500mL = precioDescartables.getPackBotellas500mL();
        }
    catch (Exception e)
        {
        }
    }


    public Object getCopia()
    {
    PrecioDescartables precioDescartables = new PrecioDescartables();
    precioDescartables.copiar(this);
    return precioDescartables;
    }


}
