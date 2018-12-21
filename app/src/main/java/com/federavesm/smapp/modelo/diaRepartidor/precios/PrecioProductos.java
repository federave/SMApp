package com.federavesm.smapp.modelo.diaRepartidor.precios;

import android.content.Context;

import com.federavesm.smapp.modelo.diaRepartidor.GenericoDiaRepartidorEvaluar;
import com.federavesm.smapp.modelo.diaRepartidor.GenericoReparto;

/**
 * Created by Federico on 4/2/2018.
 */

public abstract class PrecioProductos extends GenericoReparto {


    public PrecioProductos(Context context){super(context);}


    protected PrecioRetornables precioRetornables = new PrecioRetornables();
    protected PrecioDescartables precioDescartables = new PrecioDescartables();


    public PrecioRetornables getPrecioRetornables() {
        return precioRetornables;
    }

    public void setPrecioRetornables(PrecioRetornables precioRetornables) {
        this.precioRetornables = precioRetornables;
    }

    public PrecioDescartables getPrecioDescartables() {
        return precioDescartables;
    }

    public void setPrecioDescartables(PrecioDescartables precioDescartables) {
        this.precioDescartables = precioDescartables;
    }

    @Override
    public Object getCopia() {
        return null;
    }


    public abstract boolean getEspecial();


    @Override
    public void copiar(Object object)
    {
        try
        {
            PrecioProductos precioProductos = (PrecioProductos)object;
            this.id = precioProductos.getId();
            this.precioRetornables.copiar(precioProductos.getPrecioRetornables());
            this.precioDescartables.copiar(precioProductos.getPrecioDescartables());

        }
        catch (Exception e)
        {

        }
    }















}
