package com.federavesm.smapp.modelo.diaRepartidor.reparto;

import android.content.Context;

import com.federavesm.smapp.modelo.Comunicador;
import com.federavesm.smapp.modelo.diaRepartidor.GenericoDiaRepartidorEvaluar;
import com.federavesm.smapp.modelo.diaRepartidor.GenericoRepartoProductos;
import com.federavesm.smapp.modelo.diaRepartidor.productos.Descartables;
import com.federavesm.smapp.modelo.diaRepartidor.productos.Retornables;

import java.net.ProtocolException;

/**
 * Created by Federico on 9/2/2018.
 */




public abstract class RepartoProductos extends GenericoRepartoProductos {



    public RepartoProductos(Context context)
    {
        super(context);
    }



    protected Retornables retornables = new Retornables();
    protected Descartables descartables = new Descartables();

    public Retornables getRetornables() {
        return retornables;
    }

    public void setRetornables(Retornables retornables) {
        this.retornables = retornables;
    }

    public Descartables getDescartables() {
        return descartables;
    }

    public void setDescartables(Descartables descartables) {
        this.descartables = descartables;
    }

    @Override
    public boolean modificar() {
        return false;
    }

    @Override
    public float getDinero()
    {
    float dinero20L = this.retornables.getBidones20L() * Comunicador.getCliente().getPrecioProductos().getPrecioRetornables().getBidon20L();
    float dinero12L = this.retornables.getBidones12L() * Comunicador.getCliente().getPrecioProductos().getPrecioRetornables().getBidon12L();
    float dinero10L = this.descartables.getBidones10L() * Comunicador.getCliente().getPrecioProductos().getPrecioDescartables().getBidon10L();
    float dinero8L = this.descartables.getBidones8L() * Comunicador.getCliente().getPrecioProductos().getPrecioDescartables().getBidon8L();
    float dinero5L = this.descartables.getBidones5L() * Comunicador.getCliente().getPrecioProductos().getPrecioDescartables().getBidon5L();
    float dinero2L = this.descartables.getPackBotellas2L() * Comunicador.getCliente().getPrecioProductos().getPrecioDescartables().getPackBotellas2L();
    float dinero500mL = this.descartables.getPackBotellas500mL() * Comunicador.getCliente().getPrecioProductos().getPrecioDescartables().getPackBotellas500mL();
    this.dinero = dinero20L + dinero12L + dinero10L + dinero8L + dinero5L + dinero2L + dinero500mL;
    return dinero;
    }

    public void setDinero(){ getDinero();}


}
