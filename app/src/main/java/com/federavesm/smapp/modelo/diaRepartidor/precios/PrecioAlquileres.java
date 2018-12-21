package com.federavesm.smapp.modelo.diaRepartidor.precios;

import android.content.Context;

import com.federavesm.smapp.modelo.diaRepartidor.GenericoDiaRepartidorEvaluar;
import com.federavesm.smapp.modelo.diaRepartidor.GenericoReparto;

/**
 * Created by Federico on 4/2/2018.
 */

public abstract class PrecioAlquileres extends GenericoReparto {


    public PrecioAlquileres(Context context){super(context);}


    protected float alquiler6Bidones=0;
    protected float alquiler8Bidones=0;
    protected float alquiler10Bidones=0;
    protected float alquiler12Bidones=0;

    public float getAlquiler6Bidones() {
        return alquiler6Bidones;
    }

    public void setAlquiler6Bidones(float alquiler6Bidones) {
        this.alquiler6Bidones = alquiler6Bidones;
    }

    public float getAlquiler8Bidones() {
        return alquiler8Bidones;
    }

    public void setAlquiler8Bidones(float alquiler8Bidones) {
        this.alquiler8Bidones = alquiler8Bidones;
    }

    public float getAlquiler10Bidones() {
        return alquiler10Bidones;
    }

    public void setAlquiler10Bidones(float alquiler10Bidones) {
        this.alquiler10Bidones = alquiler10Bidones;
    }

    public float getAlquiler12Bidones() {
        return alquiler12Bidones;
    }

    public void setAlquiler12Bidones(float alquiler12Bidones) {
        this.alquiler12Bidones = alquiler12Bidones;
    }
    public abstract boolean getEspecial();


    @Override
    public void copiar(Object object)
    {
    try
        {
        PrecioAlquileres precioAlquileres = (PrecioAlquileres)object;
        this.id = precioAlquileres.getId();
        this.alquiler6Bidones = precioAlquileres.getAlquiler6Bidones();
        this.alquiler8Bidones = precioAlquileres.getAlquiler8Bidones();
        this.alquiler10Bidones = precioAlquileres.getAlquiler10Bidones();
        this.alquiler12Bidones = precioAlquileres.getAlquiler12Bidones();
        }
    catch (Exception e)
        {

        }
    }




}
