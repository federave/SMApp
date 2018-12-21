package com.federavesm.smapp.modelo;

import android.content.Context;
import android.graphics.drawable.Drawable;

/**
 * Created by Federico on 19/2/2018.
 */



public abstract class GenericoVista extends Generico {



    public GenericoVista(Context context)
    {
        super(context);
    }


    public abstract int getRecursoImagen();



}
