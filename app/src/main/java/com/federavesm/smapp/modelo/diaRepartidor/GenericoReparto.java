package com.federavesm.smapp.modelo.diaRepartidor;

import android.content.Context;

import com.federavesm.smapp.modelo.Fecha;

/**
 * Created by Federico on 2/1/2018.
 */

public abstract class GenericoReparto extends GenericoDiaRepartidorEvaluar {



    public GenericoReparto(Context context)
    {
        super(context);
    }





    protected int idDiaRepartidor;

    public int getIdDiaRepartidor(){return idDiaRepartidor;}
    public void setIdDiaRepartidor(int idDiaRepartidor){this.idDiaRepartidor = idDiaRepartidor;}







}
