package com.federavesm.smapp.modelo.resumenRepartidor;

import android.content.Context;

import com.federavesm.smapp.modelo.Conector;
import com.federavesm.smapp.modelo.Fecha;

public class ResumenRepartidor extends Conector{




    public ResumenRepartidor(Context context)
    {
    super(context);
    }



    protected int idRepartidor;
    protected Fecha fechaInicio;
    protected Fecha fechaFin;




    public void cargar()
    {

    }























    public int getIdRepartidor() {
        return idRepartidor;
    }

    public void setIdRepartidor(int idRepartidor) {
        this.idRepartidor = idRepartidor;
    }

    public Fecha getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Fecha fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Fecha getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Fecha fechaFin) {
        this.fechaFin = fechaFin;
    }








}
