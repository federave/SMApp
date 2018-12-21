package com.federavesm.smapp.modelo.diaRepartidor;

import android.content.Context;

import com.federavesm.smapp.modelo.diaRepartidor.productos.Descartables;
import com.federavesm.smapp.modelo.diaRepartidor.productos.Retornables;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.ventaProductos.VentaProductos;

/**
 * Created by Federico on 24/2/2018.
 */




public abstract class GenericoRepartoProductos extends GenericoDiaRepartidorEvaluar {



    public GenericoRepartoProductos(Context context)
    {
        super(context);
    }


    protected float dinero;

    public abstract float getDinero();





    @Override
    public void copiar(Object object)
    {
    try
        {
        GenericoRepartoProductos genericoRepartoProductos = (GenericoRepartoProductos)object;
        this.dinero = genericoRepartoProductos.getDinero();
        }
    catch (Exception e)
        {

        }
    }
















}
