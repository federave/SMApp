package com.federavesm.smapp.modelo.diaRepartidor;

import android.content.Context;

import com.federavesm.smapp.modelo.diaRepartidor.reparto.RepartoProductos;

/**
 * Created by Federico on 22/2/2018.
 */




public abstract class GenericoDiaRepartidorProductos extends RepartoProductos {



    public GenericoDiaRepartidorProductos(Context context)
    {
        super(context);
    }





    protected int idDiaRepartidor;

    public int getIdDiaRepartidor(){return idDiaRepartidor;}
    public void setIdDiaRepartidor(int idDiaRepartidor){this.idDiaRepartidor = idDiaRepartidor;}







}
