package com.federavesm.smapp.modelo.diaRepartidor;

import android.content.Context;

import com.federavesm.smapp.modelo.Generico;

/**
 * Created by Federico on 8/2/2018.
 */


public abstract class GenericoDiaRepartidor extends Generico {



    public GenericoDiaRepartidor(Context context)
    {
        super(context);
    }



    public abstract boolean getEstado();







}