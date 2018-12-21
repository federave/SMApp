package com.federavesm.smapp.modelo;

import android.content.Context;

/**
 * Created by Federico on 2/1/2018.
 */

public abstract class Generico extends Conector {



    public Generico(Context context)
    {
    super(context);
    this.context = context;
    }


    protected Context context;


    public abstract boolean guardar();
    public abstract boolean modificar();
    public abstract boolean eliminar();
    public abstract boolean actualizar();
    public abstract boolean cargar();

    public abstract Object getCopia();

    public abstract void copiar(Object object);



    protected int id=-1;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }




}
