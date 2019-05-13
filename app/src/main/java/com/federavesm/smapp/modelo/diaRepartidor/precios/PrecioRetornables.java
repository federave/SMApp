package com.federavesm.smapp.modelo.diaRepartidor.precios;

/**
 * Created by Federico on 4/2/2018.
 */

public class PrecioRetornables {


    public PrecioRetornables(){}

    protected float bidon20L=0;
    protected float bidon12L=0;




    public boolean esIgual(PrecioRetornables precio)
    {
        if(this.bidon20L == precio.getBidon20L() && this.bidon12L==precio.getBidon12L())
            return true;
        else
            return false;
    }





    public float getBidon20L() {
        return bidon20L;
    }

    public void setBidon20L(float bidon20L) {
        this.bidon20L = bidon20L;
    }

    public float getBidon12L() {
        return bidon12L;
    }

    public void setBidon12L(float bidon12L) {
        this.bidon12L = bidon12L;
    }


    public void copiar(Object object)
    {
        try
        {
            PrecioRetornables precioRetornables = (PrecioRetornables)object;
            this.bidon20L = precioRetornables.getBidon20L();
            this.bidon12L= precioRetornables.getBidon12L();
        }
        catch (Exception e)
        {
        }
    }


    public Object getCopia()
    {
    PrecioRetornables precioRetornables = new PrecioRetornables();
    precioRetornables.copiar(this);
    return precioRetornables;
    }




}
